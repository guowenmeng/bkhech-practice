package com.bkhech.home.practice.javacore.concurrent.locks;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平非公平锁样例
 * @author guowm
 * @date 2021/3/1
 */
public class FairAndUnfairDemo {
    private static final Lock fairLock = new ReentrantLockCustom(true);
    private static final Lock unfairLock = new ReentrantLockCustom(false);

    /**
     * i: 0, Locked by: 0, Waiting by: [1, 2, 3, 4]
     * i: 0, Locked by: 1, Waiting by: [2, 3, 4, 0]
     * i: 0, Locked by: 2, Waiting by: [3, 4, 0, 1]
     * i: 0, Locked by: 3, Waiting by: [4, 0, 1, 2]
     * i: 0, Locked by: 4, Waiting by: [0, 1, 2, 3]
     * i: 1, Locked by: 0, Waiting by: [1, 2, 3, 4]
     * i: 1, Locked by: 1, Waiting by: [2, 3, 4]
     * i: 1, Locked by: 2, Waiting by: [3, 4]
     * i: 1, Locked by: 3, Waiting by: [4]
     * i: 1, Locked by: 4, Waiting by: []
     * @throws Exception
     */
    @Test
    public void fair() throws Exception {
        testLock(fairLock);
    }

    /**
     * i: 0, Locked by: 0, Waiting by: [1, 2, 3, 4]
     * i: 0, Locked by: 1, Waiting by: [2, 3, 4, 0]
     * i: 1, Locked by: 1, Waiting by: [2, 3, 4, 0]
     * i: 0, Locked by: 2, Waiting by: [3, 4, 0]
     * i: 1, Locked by: 2, Waiting by: [3, 4, 0]
     * i: 0, Locked by: 3, Waiting by: [4, 0]
     * i: 1, Locked by: 3, Waiting by: [4, 0]
     * i: 0, Locked by: 4, Waiting by: [0]
     * i: 1, Locked by: 4, Waiting by: [0]
     * i: 1, Locked by: 0, Waiting by: []
     * @throws Exception
     */
    @Test
    public void unfair() throws Exception {
        testLock(unfairLock);
    }

    private void testLock(Lock lock) throws InterruptedException {
        // 启动5个Job
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(lock)) {
                @Override
                public String toString() {
                    return super.getName();
                }
            };
            thread.setName(String.valueOf(i));
//            thread.setDaemon(true);
            thread.start();
        }
        Thread.sleep(11000);
    }

    private static class Job implements Runnable {
        private final Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            // 连续2次打印当前的Thread和等待队列中的Thread
            // 注：第一次（i=1）先于第二次（i=2）获取锁
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.printf("i: %s, Locked by: %s, Waiting by: %s \n", i, Thread.currentThread().getName(), ((ReentrantLockCustom) lock).getQueuedThreads());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

    /**
     * 重新实现ReentrantLock类是为了重写getQueuedThreads方法，便于我们试验。
     * 该类主要公开了getQueuedThreads()方法，该方法返回正在等待获取锁的线程列表，
     * 由于列表是逆序输出，为了方便观察结果，将其进行反转。
     */
    private static class ReentrantLockCustom extends ReentrantLock {
        public ReentrantLockCustom(boolean fair) {
            super(fair);
        }

        //获取同步队列中的线程
        @Override
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
