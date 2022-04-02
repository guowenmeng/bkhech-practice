package com.bkhech.home.practice.javacore.concurrent.locks.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义有界队列 FIFO
 * <p>
 * 有界队列是一种特殊的队列，
 * 当队列为空时，队列的获取操作将会阻塞获取线程，直到队列中有新增元素，
 * 当队列已满时，队列的插入操作将会阻塞插入线程，直到队列出现“空位”
 * @author guowm
 * @date 2021/3/2
 */
public class BoundedQueueWithCondition<T> {

    private final Object[] items;
    // 添加的下标，删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private final Lock lock = new ReentrantLock();
    /**
     * 非空条件
     */
    private final Condition notEmpty = lock.newCondition();
    /**
     * 非满条件
     */
    private final Condition notFull = lock.newCondition();

    public BoundedQueueWithCondition(int size) {
        items = new Object[size];
    }

    /**
     * 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
     *
     * 流程分析：
     * 首先需要获得锁，目的是确保数组修改的可见性和排他性。当数组数量等于数组长度时，
     * 表示数组已满，则调用notFull.await()，当前线程随之释放锁并进入等待状态。如果数组数量不
     * 等于数组长度，表示数组未满，则添加元素到数组中，同时通知等待在notEmpty上的线程，数
     * 组中已经有新元素可以获取。
     *
     * 在添加和删除方法中使用while循环而非if判断，目的是防止过早或意外的通知，只有条件
     * 符合（数组不满）才能够退出循环。
     *
     * @param t
     * @throws InterruptedException
     */
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            // 数组满，使用while作用是指定条件下（数组不满）唤醒才算数
            // 当前线程将一直处于休眠状态，可能的唤醒情况包括：
            // 1. 其他某个线程调用此 Condition 的 signal() 方法，并且碰巧将当前线程选为被唤醒的线程；
            // 2. 其他某个线程调用此 Condition 的 signalAll() 方法；
            // 3. 其他某个线程中断当前线程，且支持中断线程的挂起；
            // 4. 发生“虚假唤醒”（这通常作为对基础平台语义的让步）
            while (count == items.length) {
                // 释放锁并在此处阻塞，等待唤醒
                notFull.await();
            }

            // 在addIndex 添加的下标 处，插入数据
            items[addIndex] = t;
            // addIndex加1，和当前数组长度对比，若相等，说明下标到达数组尾部，将addIndex重置为0，循环使用数组
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            // 数组当前数量 +1
            ++count;
            // 唤醒一个正在等待获取元素的线程，如果有正在等待获取元素的线程的话
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
     *
     * @return
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

}
