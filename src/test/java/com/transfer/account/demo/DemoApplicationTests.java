package com.transfer.account.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thread.demo.DemoApplication;
import com.thread.demo.config.JmsConfig;
import com.thread.demo.entity.TransferOrderTable;
import com.thread.demo.service.Consumer;
import com.thread.demo.service.Producer;
import com.thread.demo.service.TaskService;
import com.thread.demo.service.TransferAccountService;
import com.thread.demo.service.impl.AccountServiceImpl;
import com.thread.demo.service.impl.MessageTableServiceImpl;
import com.thread.demo.service.impl.TesttableServiceImpl;
import com.thread.demo.service.impl.TransferOrderTableServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
    @Autowired
    private TransferAccountService transferAccountService;
    @Autowired
    private TransferOrderTableServiceImpl transferOrderTableService;
    @Autowired
    private TaskService taskService;

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

    public static ReentrantLock reLock1 = new ReentrantLock(false);//创建一个非公平锁

    public static ReentrantLock reLock2 = new ReentrantLock(false);//创建一个非公平锁

    public static void main(String[] args) throws InterruptedException {
/*      Demo d1 = new Demo(1);
        Demo d2 = new Demo(2);
        d1.start();
        d2.start();
        Thread.sleep(1000);
        d2.interrupt();*/

        String str = "dawada";

        Integer.parseInt("91283472332");
    }


    static class Demo extends Thread {
        int lock;

        public Demo(int lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程启动");
                /*
                if (this.lock == 1) {
                    reLock1.lockInterruptibly();//获取锁
                    System.out.println("线程1获取到了锁1");
                    Thread.sleep(1000);
                    reLock2.lockInterruptibly();;//获取锁
                    System.out.println("线程1获取到了锁2");
                } else {
                    reLock2.lockInterruptibly();;//获取锁
                    System.out.println("线程2获取到了锁2");
                    Thread.sleep(1000);
                    reLock1.lockInterruptibly();;//获取锁
                    System.out.println("线程2获取到了锁1");
                }
                 */
                if(reLock1.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println("lock" + lock + "获取到了锁1");
                    Thread.sleep(5000);
                }else{
                    System.out.println("lock" + lock + "获取锁1失败");
                }

//                if(reLock2.tryLock(3, TimeUnit.SECONDS)){
//                    System.out.println("lock" + lock + "获取到了锁2");
//                    Thread.sleep(5000);
//                }else{
//                    System.out.println("lock" + lock + "获取锁2失败");
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放锁
                if(reLock1.isHeldByCurrentThread()){
                    reLock1.unlock();
                }
                if(reLock2.isHeldByCurrentThread()){
                    reLock2.unlock();
                }
                System.out.println("lock" + lock + "执行结束");
            }
        }
    }


    public static void modifyList(List<Integer> list){
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
    }



}

