package com.thread.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.config.JmsConfig;
import com.thread.demo.entity.Account;
import com.thread.demo.entity.MessageTable;
import com.thread.demo.entity.TransferOrderTable;
import com.thread.demo.enums.MessageEnum;
import com.thread.demo.enums.MessageTypeEnum;
import com.thread.demo.service.impl.AccountServiceImpl;
import com.thread.demo.service.impl.TransferOrderTableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author LiuQi
 * @date 2020/11/24 13:31
 */
@Service
@Slf4j
public class TransferAccountService {
    private final IMessageTableService iMessageTableService;
    private final Producer producer;
    private final AccountServiceImpl iAccountService;
    private final TransferOrderTableServiceImpl transferOrderTableService;

    @Autowired
    public TransferAccountService(IMessageTableService iMessageTableService,
                                  Producer producer,
                                  AccountServiceImpl iAccountService,
                                  TransferOrderTableServiceImpl transferOrderTableService){
        this.iMessageTableService = iMessageTableService;
        this.producer = producer;
        this.iAccountService = iAccountService;
        this.transferOrderTableService = transferOrderTableService;
    }

    /**
     * 总结
     * 1.创建一个消息数据库表 每次调用服务生成一个唯一消息id 将这条消息落库并记录消息状态为待发送的状态 返回消息id进行后面的操作
     * （我们通常是比较忌讳在事务中做远程调用处理的，远程调用的性能和时间往往不可控，会导致当前事务变为一个大事务，从而引发其他故障。）
     *
     * 2.开启事务 进行主要的订单业务操作 保存订单信息时将消息id和订单关联起来  提交事务
     *
     * 3.如果 以上步骤都成功 调用消息服务 将消息发送到mq
     *
     * 如何设计rocketmq的消息回查机制
     * 消息状态为本地事务执行完毕 发送消息到mq  mq消息被消费端消费记录在消费端的log日志表中
     * 根据消费log表中新增的消费信息 回查生产表的消息表并将消息状态更新为已消费
     * 定时重发服务端消息表中未被更新状态的消息（可以设置最大重发次数 超过次数的消息采用人工处理的方式）
     *
     * 定时任务 本地事务执行成功后消息重发到服务端 （目的解决mq 发送消息 失败的情况）
     *
     * 以上解决的问题主要是确保消息可以发送到消息队列中 关于消费端保证消费成功与否还是需要酌情处理 消费失败多数是代码问题
     * 定时任务对账 两个数据库的转账log表 时间跨度为一天 重发消息（目的解决消费端 消费失败 的情况）
     *
     */

    //本地事务 发送mq到消费端
    public void  generateOrder(MessageTypeEnum messageTypeEnum, String accountName, String toAccount,Integer money) {
        //生成转账订单前  落库预消息
        int messageId = iMessageTableService.insertMessage(messageTypeEnum.getMemo());
        //创建订单预备消息  测试消息能否发送成功  此消息在客户端不可见  不做消费
        Message message = new Message(JmsConfig.PREPARETOPIC, "prepare-message", ("预备消息发送").getBytes());
        try {
            producer.getProducer().send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Account account = iAccountService.getOneMaster(new QueryWrapper<Account>().eq("person_account", accountName));
        //开启事务 进行业务转账  执行本地事务失败则回滚  执行本地事务成功如何查看消息是否发送到mq成功 新建定时任务表 a端预消息表  b端转账日志表
        //mq发送消息返回send_ok也不一定是一定可靠的  如果想要更加可靠可以设置SYNC_FLUSH同步刷盘  mq持久化成功后才会返回send_ok
        if(account == null){
            log.error("转账账户不能为空");
            return;
        }

        Account account1 = Optional.ofNullable(iAccountService.getOneMaster(new QueryWrapper<Account>().eq("person_account", accountName)))
                                .orElseThrow(() -> {
                                    log.error("转账账户不能为空");
                                    return  new RuntimeException();
                                });

        try{
            iAccountService.transferAccount(messageTypeEnum, accountName, toAccount, money, account, messageId);
        }catch (Exception e){
            log.info("转账失败={}",account);
            MessageTable messageTable = new MessageTable();
            messageTable.setId(messageId);
            messageTable.setStatus(MessageEnum.FAIL_SEND.getValue());
            messageTable.setMessage("转账失败");
            iMessageTableService.updateById(messageTable);
            return;
        }
        //本地事务执行完毕  发送消息
        Message transferMsg = new Message(JmsConfig.TOPIC, "testtag", String.valueOf(messageId).getBytes());
        //发送
        try {
            SendResult sendResult = producer.getProducer().send(transferMsg);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consumerAddMoney(String msg){
        TransferOrderTable transferOrderTableMaster = transferOrderTableService.getOneMaster(new QueryWrapper<TransferOrderTable>().eq("msg_id",Integer.parseInt(msg)));

        //从消费端log表获取数据
        TransferOrderTable transferOrderTableDb1 = transferOrderTableService.getOneDb1(new QueryWrapper<TransferOrderTable>().eq("msg_id",Integer.parseInt(msg)));

        iAccountService.addMoney(transferOrderTableMaster, transferOrderTableDb1);
    }



    //mq发送消息 并更新本地事务消息表状态
    public void sendMessageToConsumer(Integer messageId){
        Message transferMsg = new Message(JmsConfig.TOPIC, "testtag", String.valueOf(messageId).getBytes());
        //发送
        try {
            SendResult sendResult = producer.getProducer().send(transferMsg);
            //发送成功
            if(sendResult != null && SendStatus.SEND_OK.equals(sendResult.getSendStatus())){
                MessageTable messageTable = new MessageTable();
                messageTable.setId(messageId);
                messageTable.setStatus(MessageEnum.SUCCESS_SEND.getValue());
                messageTable.setMessage(MessageEnum.SUCCESS_SEND.getMemo());
                iMessageTableService.updateById(messageTable);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
