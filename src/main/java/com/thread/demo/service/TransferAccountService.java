package com.thread.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author LiuQi
 * @date 2020/11/24 13:31
 */
@Service
public class TransferAccountService {
    /**
     * 总结
     * 1.创建一个消息数据库表 每次调用服务生成一个唯一消息id 将这条消息落库并记录消息状态为待发送的状态 返回消息id进行后面的操作
     * （我们通常是比较忌讳在事务中做远程调用处理的，远程调用的性能和时间往往不可控，会导致当前事务变为一个大事务，从而引发其他故障。）
     *
     * 2.开启事务 进行主要的订单业务操作 保存订单信息时将消息id和订单关联起来  提交事务
     *
     * 3.如果 以上步骤都成功 调用消息服务 将消息发送到mq
     */



}
