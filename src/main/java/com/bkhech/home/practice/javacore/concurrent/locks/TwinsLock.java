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
 * @description 需求：设计一个同步工具：该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的
 * 访问将被阻塞，我们将这个同步工具命名为TwinsLock。
 * <p>
 * <p>
 * 首先，确定访问模式。TwinsLock能够在同一时刻支持多个线程的访问，这显然是共享式
 * 访问，因此，需要使用同步器提供的acquireShared(int args)方法等和Shared相关的方法，这就要
 * 求TwinsLock必须重写tryAcquireShared(int args)方法和tryReleaseShared(int args)方法，这样才能
 * 保证同步器的共享式同步状态的获取与释放方法得以执行。
 * <p>
 * 其次，定义资源数。TwinsLock在同一时刻允许至多两个线程的同时访问，表明同步资源
 * 数为2，这样可以设置初始状态status为2，当一个线程进行获取，status减1，该线程释放，则
 * status加1，状态的合法范围为0、1和2，其中0表示当前已经有两个线程获取了同步资源，此时
 * 再有其他线程对同步状态进行获取，该线程只能被阻塞。在同步状态变更时，需要使用
 * compareAndSet(int expect,int update)方法做原子性保障。
 * <p>
 * 最后，组合自定义同步器。前面的章节提到，自定义同步组件通过组合自定义同步器来完
 * 成同步功能，一般情况下自定义同步器会被定义为自定义同步组件的内部类。
 *
 *
 * @see Semaphore
 * 可用 Semaphore semaphore = new Semaphore(2); 替换
 */
public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) == 1;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    /**
     * 静态内部类，自定义同步器
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(2);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current,
                        newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

    }

}
