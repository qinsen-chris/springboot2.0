package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Create by qs on 2019/1/18
 * email:qinsen@chinaredsun.com
 * 缓存失效导致系统挂掉
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheStampedingTests {

    long timed = 0L;

    @Before
    public void start(){
        System.out.println("开始测试！");
        timed = System.currentTimeMillis();
    }

    @After
    public void end(){
        System.out.println("结束测试！执行时长："+(System.currentTimeMillis()-timed));
    }

    //车次
    private static final  String TICKET_SEQ = "G41";

    //模拟请求的数量
    private static final int THREAD_NUM = 10;

    //倒计时器，juc中常用的工具类
    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    @Test
    public void beachmark() throws InterruptedException {
        //创建并不是马上发起请求
        Thread[] threads = new Thread[THREAD_NUM];

        for(int i=0;i<THREAD_NUM;i++){
            //多线程模拟用户查询请求
            Thread thread = new Thread(()->{
                try {
                    //代码在这里等待，等待countDownLatch为0，代表所有线程都start,在运行后续的代码
                    countDownLatch.await();

                    //http请求，实际上就是多线程调用这个方法
                    // 业务代码实现逻辑 ：先从缓存中获取，没有的话去数据库查找，然后重建缓存，如果缓存失效,则大并发会对数据库造成压力
                    //不要让请求同时进入，做同步锁。不要使用synchronized,使用lock（lock()、unlock()）,在锁中在做一次判断，判断缓存中是否有数据。
                    // ticketService.queryTicketStock(TICKET_SEQ);
                    //每种车次一个锁，不要所有车次同一个锁

                    System.out.println("开始执行业务代码！");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            threads[i] = thread;
            thread.start();

            //启动后，倒计时器倒计数，减一，代表又有一个线程就绪了
            countDownLatch.countDown();
        }

        //等待上面所有线程执行完毕之后，结束测试
        for(Thread  thread : threads){
            thread.join();
        }

    }
}
