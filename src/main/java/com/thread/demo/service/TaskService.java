package com.thread.demo.service;

import com.alibaba.fastjson.JSON;
import com.thread.demo.config.JmsConfig;
import com.thread.demo.entity.MessageTable;
import com.thread.demo.entity.TransferAccountLog;
import com.thread.demo.entity.TransferOrderTable;
import com.thread.demo.enums.MessageEnum;
import com.thread.demo.service.impl.AccountServiceImpl;
import com.thread.demo.service.impl.TransferAccountLogServiceImpl;
import com.thread.demo.service.impl.TransferOrderTableServiceImpl;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiuQi
 * @date 2020/11/27 13:05
 */
@Component
public class TaskService {
    private final IMessageTableService iMessageTableService;
    private final Producer producer;
    private final AccountServiceImpl iAccountService;
    private final TransferOrderTableServiceImpl transferOrderTableService;
    private final TransferAccountService transferAccountService;
    private final TransferAccountLogServiceImpl transferAccountLogService;

    @Autowired
    public TaskService(IMessageTableService iMessageTableService,
                       Producer producer,
                       AccountServiceImpl iAccountService,
                       TransferOrderTableServiceImpl transferOrderTableService,
                       TransferAccountService transferAccountService,
                       TransferAccountLogServiceImpl transferAccountLogService){
        this.iMessageTableService = iMessageTableService;
        this.producer = producer;
        this.iAccountService = iAccountService;
        this.transferOrderTableService = transferOrderTableService;
        this.transferAccountService = transferAccountService;
        this.transferAccountLogService = transferAccountLogService;
    }

    //两个定时任务  1.回写消费成功状态到生产端message表   2.重发状态为本地事务完成的单据（有没有必要 rocketmq已经有重发机制）

    //查询状态为本地事务执行完毕   未发送到mq的单据进行重发
    //一分钟一次
    @Scheduled(cron = "0 0/1 * * * ?")
    public void  resendMsg(){
        //定时任务 以消费表order中最大的时间作为结束时间  用上次执行定时任务最大的时间作为开始时间  将这段时间内的数据msgid回写成功消费到服务端message表
        LocalDateTime oldMaxTime = transferAccountLogService.findLastTime();

        List<TransferOrderTable> transferOrderTables = transferOrderTableService.findMsgIdByTimeMaster(oldMaxTime);

        LocalDateTime newMaxTime = transferOrderTables.stream().sorted(Comparator.comparing(TransferOrderTable::getAccountCreateTime)).map(TransferOrderTable::getAccountCreateTime).collect(Collectors.toList()).get(0);

        List<Integer> msgIds = transferOrderTables.stream().map(TransferOrderTable::getMsgId).collect(Collectors.toList());

        for(Integer messageId : msgIds){
            //本地事务执行完毕  发送消息 如果此步骤发生错误 另起定时任务去message表中查询本地执行完成状态的消息 重新发送
            transferAccountService.sendMessageToConsumer(messageId);
        }

        TransferAccountLog transferAccountLog = new TransferAccountLog();
        transferAccountLog.setMsgId(JSON.toJSONString(msgIds));
        transferAccountLog.setEndTime(newMaxTime);
        transferAccountLog.setStartTime(oldMaxTime);
        transferAccountLogService.save(transferAccountLog);
    }

    //定时任务对账 两个数据库的转账log表  重发消息（目的解决消费端 消费失败 的情况） 区分状态 筛选的是发送成功状态的
    //使用定时任务log表记录每次定时任务执行对账的最终时间  然后下次定时任务从上次最后执行开始到当前时间结束 startTime endTime






}
