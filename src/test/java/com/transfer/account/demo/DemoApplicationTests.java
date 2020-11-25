package com.transfer.account.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.DemoApplication;
import com.thread.demo.config.JmsConfig;
import com.thread.demo.entity.Account;
import com.thread.demo.entity.MessageTable;
import com.thread.demo.entity.Testtable;
import com.thread.demo.service.Consumer;
import com.thread.demo.service.Producer;
import com.thread.demo.service.impl.AccountServiceImpl;
import com.thread.demo.service.impl.MessageTableServiceImpl;
import com.thread.demo.service.impl.TesttableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class DemoApplicationTests {
    @Autowired
    TesttableServiceImpl testtableService;
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private MessageTableServiceImpl messageTableService;

    private List<String> mesList;

    /**
     * 初始化消息
     */
    public DemoApplicationTests() {
        mesList = new ArrayList<>();
        mesList.add("小小");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");
    }

    @Test
    public void callback() throws Exception {
        //总共发送五次消息
        for (String s : mesList) {
            //创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "testtag", ("小小一家人的称谓:" + s).getBytes());
            //发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}",sendResult);
        }

        System.out.println("success");
    }

    @Test
    public void testMoreDataBases(){
//        QueryWrapper wrapper = new QueryWrapper<Account>();
//        wrapper.eq("person_account", 123456);
//
//        List<Account> accounts = accountService.getBaseMapper().selectList(wrapper);
//
//        List<Account> accounts1 = accountService.seletctAllAccount();
//
//        Testtable testtables = testtableService.getBaseMapper().selectAllNum();
//
//        System.out.println(1);


        System.out.println("预发消息id"+messageTableService.insertMessage("hello"));

    }

}

