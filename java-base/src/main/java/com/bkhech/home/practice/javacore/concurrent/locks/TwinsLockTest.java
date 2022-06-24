package com.bkhech.home.practice.javacore.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author guowm
 * @date 2021/3/1
 * @description 运行该测试用例，可以看到线程名称成对输出，也就是在同一时刻只有两个线程能够获取到锁，
 * 这表明TwinsLock可以按照预期正确工作。
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        final TwinsLockTest twinsLockTest = new TwinsLockTest();
        twinsLockTest.test();
    }

    public void test() {
        final Lock lock = new TwinsLock();
//                Semaphore semaphore = new Semaphore(2);

        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
//                    try {
//                        semaphore.acquire();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        secondSleep(1);
                        System.out.println(Thread.currentThread().getName());
                        secondSleep(1);
                    } finally {
                        lock.unlock();
//                        semaphore.release();
                    }
                }
            }
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            secondSleep(1);
            System.out.println();
        }

    }

    private void secondSleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
