package com.bkhech.home.practice.javacore.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author guowm
 * @date 2021/3/1
 * @description
 */
public class MutexLockThreadPoolExecutorWorkerCountOfDemo {

    public static void main(String[] args) {
        final MutexLockThreadPoolExecutorWorkerCountOfDemo main =
                new MutexLockThreadPoolExecutorWorkerCountOfDemo();
        main.test();
    }

    public void test() {
        final Lock lock = new MutexLock();
//        Semaphore semaphore = new Semaphore(1);

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
