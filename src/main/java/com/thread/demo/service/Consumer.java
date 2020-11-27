package com.thread.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.entity.TransferOrderTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.thread.demo.config.JmsConfig;

import java.io.UnsupportedEncodingException;

/**
 * @author LiuQi
 * @date 2020/11/23 15:46
 */
@Slf4j
@Component
public class Consumer {
    @Autowired
    private TransferAccountService transferAccountService;


    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "test_consumer";

    /**
     * 通过构造函数 实例化对象
     */
    public Consumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        //不开启vip通道 开通口端口会减2
        consumer.setVipChannelEnabled(false);

        consumer.setInstanceName("consumer1");

        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(JmsConfig.TOPIC, "*");

        //集群消费模式（推荐）  同一个消费组中 一条消息只会被一个消费者消费 上次消费节点记录在broker上  可以设置多个消费者不同消费组模拟广播消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //广播消费模式 同一个消费组中 一条消息会被至少（每一个）消费者消费  上次消费节点记录在消费者上   缺点： 更容易造成消息重复消费
        //consumer.setMessageModel(MessageModel.BROADCASTING);

        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {
                    //消费者获取消息  进行相关业务处理  业务用事务包裹回滚
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);

                    transferAccountService.consumerAddMoney(body);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //如果消费时出现异常 业务方消费失败，之后进行重新尝试消费
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("消费者 启动成功=======");
    }


}
