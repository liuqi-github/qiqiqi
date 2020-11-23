package test.thread.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.thread.demo.config.JmsConfig;
import test.thread.demo.controller.TesttableController;
import test.thread.demo.service.Consumer;
import test.thread.demo.service.Producer;
import test.thread.demo.service.impl.TesttableServiceImpl;
import java.util.*;


@SpringBootTest
@Slf4j
class DemoApplicationTests {
    @Autowired
    TesttableServiceImpl testtableService;
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;

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
        mesList.add("大少奶奶");

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



}

