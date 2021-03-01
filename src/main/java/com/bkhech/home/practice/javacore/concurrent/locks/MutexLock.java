package com.bkhech.home.practice.javacore.concurrent.locks;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author guowm
 * @date 2021/3/1
 * @description 独占锁就是在同一时刻只能有一个线程获取到锁，而其他获取锁的线程只能
 * 处于同步队列中等待，只有获取锁的线程释放了锁，后继的线程才能够获取锁。
 * <p>
 * 独占锁Mutex是一个自定义同步组件，它在同一时刻只允许一个线程占有
 * 锁。Mutex中定义了一个静态内部类，该内部类继承了同步器并实现了独占式获取和释放同步
 * 状态。在tryAcquire(int acquires)方法中，如果经过CAS设置成功（同步状态设置为1），则代表获
 * 取了同步状态，而在tryRelease(int releases)方法中只是将同步状态重置为0。用户使用Mutex时
 * 并不会直接和内部同步器的实现打交道，而是调用Mutex提供的方法，在Mutex的实现中，以获
 * 取锁的lock()方法为例，只需要在方法实现中调用同步器的模板方法acquire(int args)即可，当
 * 前线程调用该方法获取同步状态失败后会被加入到同步队列中等待，这样就大大降低了实现
 * 一个可靠自定义同步组件的门槛
 *
 * 缺点：
 * 当一个线程调用Mutex的lock()方法获取锁之后，如果再次调用lock()方法，则该线程将会被自己所阻塞，
 * 原因是Mutex在实现tryAcquire(int acquires)方法时没有考虑占有锁的线程再次获取锁的场景，而在调用
 * tryAcquire(int acquires)方法时返回了false，导致该线程被阻塞。
 * 简单地说，Mutex是一个不支持重进入的锁。
 *
 * @see Semaphore
 * 可用 Semaphore semaphore = new Semaphore(1); 替换
 */
public class MutexLock implements Lock {

    /**
     * 仅需要将操作代理到Sync上即可
     */
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    /**
     * 静态内部类，自定义同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
            // 默认0
            setState(0);
        }

        // 是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        @Override
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁，将状态设置为0
        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {
                throw new
                        IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 返回一个Condition，每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

}
