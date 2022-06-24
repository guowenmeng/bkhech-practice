package com.bkhech.home.practice.javacore.collection.custom_queue;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义队列测试类
 *
 * @author guowm
 * @date 2021/8/26
 */
@Slf4j
public class LinkedMyQueueDemo {
    // 要测试队列
    private final static MyQueue<String> queue = new LinkedMyQueue<>();

    // 生产者
    class Producer implements Runnable {
        private final String message;

        Producer(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                final boolean success = queue.put(message);
                if (success) {
                    log.info("put {} success.", message);
                    return;
                }
                log.info("put {} fail.", message);
            } catch (Exception e) {
                log.error("put {} fail.", message);
            }
        }
    }

    // 消费者
    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                String message = queue.take();
                log.info("consumer message: {}", message);
            } catch (Exception e) {
                log.error("consumer message fail", e);
            }

        }
    }

    class MyThreadFactory implements ThreadFactory {

        private final String prefix;

        private MyThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        AtomicInteger counter = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(prefix + "-" + counter.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }

    // 场景测试
    public static void main(String[] args) throws IOException {
        final LinkedMyQueueDemo linkedMyQueueDemo = new LinkedMyQueueDemo();
        //        linkedMyQueueDemo.debugTest();
        linkedMyQueueDemo.pressureTest();
        System.in.read();
    }

    /**
     * 单元（debug）测试
     */
    void debugTest() {
        queue.put("1");
        queue.put("2");

        final String ret = queue.take();
        System.out.println(ret);
        final String ret1 = queue.take();
        System.out.println(ret1);
        final String ret2 = queue.take();
        System.out.println(ret2);
    }

    /**
     * 性能（压力）测试
     */
    void pressureTest() {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(10, 10,
                        0, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(),
                        new MyThreadFactory("testMyQueue"));

        // 压力测试
        for (int i = 0; i < 1000; i++) {
            // 是偶数的话，提交一个生产者，奇数的话，提交一个消费者
            if (i % 2 == 0) {
                executor.submit(new Producer(String.valueOf(i)));
                continue;
            }
            executor.submit(new Consumer());
        }
    }

}
