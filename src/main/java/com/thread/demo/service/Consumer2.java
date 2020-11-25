package com.thread.demo.service;

import com.thread.demo.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;


/**
 * @author LiuQi
 * @date 2020/11/23 15:46
 */
@Slf4j
@Component
public class Consumer2 {
    /**
     * 通过构造函数 实例化对象
     */
    public Consumer2() throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer");
        //不开启vip通道 开通口端口会减2
        consumer.setVipChannelEnabled(false);

        //Caused by: org.apache.rocketmq.client.exception.MQClientException: The consumer group[test_consumer] has been created before, specify another name please.
        consumer.setInstanceName("consumer2");

        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(JmsConfig.TOPIC, "*");

        //集群消费模式（推荐）
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //广播消费模式
        //consumer.setMessageModel(MessageModel.BROADCASTING);

        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {
                    //消费者获取消息  进行相关业务处理  业务用事务包裹回滚
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer2-获取消息-主题topic为={}, 消费消息为={}", msg.getTopic(), body);

                }
            } catch (Exception e) {
                e.printStackTrace();
                //如果消费时出现异常 业务方消费失败，之后进行重新尝试消费
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("消费者2 启动成功=======");
    }
}
