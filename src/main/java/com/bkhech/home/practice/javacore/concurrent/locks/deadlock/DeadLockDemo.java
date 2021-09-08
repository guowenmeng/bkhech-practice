package com.bkhech.home.practice.javacore.concurrent.locks.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 死锁样例
 * @author guowm
 * @date 2021/9/8
 */
public class DeadLockDemo {
    private static final Logger log = LoggerFactory.getLogger(DeadLockDemo.class);

    // 共享变量1
    private static final Object share1 = new Object();
    // 共享变量2
    private static final Object share2 = new Object();

    public void deadlock() throws InterruptedException {
        // 初始化线程 1 ，线程 1 需要在锁定 share1 共享资源的情况下再锁定 share2
        Thread thread1 = new Thread(() -> {
           synchronized (share1) {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (share2) {
                   log.info("{} is running", Thread.currentThread().getName());
               }
           }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (share2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (share1) {
                    log.info("{} is running", Thread.currentThread().getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new DeadLockDemo().deadlock();
        Thread.sleep(1000000000);
    }
}
