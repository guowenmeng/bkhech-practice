package com.bkhech.home.practice.javacore.concurrent;

import sun.misc.Unsafe;

/**
 * UnSafe样例
 * 原子更新变量
 *  详情查看 {@link java.util.concurrent.ThreadPoolExecutor} 中使用
 * @author guowm
 * @date 2021/9/10
 */
public class UnsafeDemo {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (UnsafeDemo.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (UnsafeDemo.class.getDeclaredField("head"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * 如果当前状态值等于预期值，则原子地将同步状态设置为给定的更新值。 此操作具有volatile读写的内存语义
     * @param expect
     * @param update
     * @return
     */
    public final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * CAS 头域。 仅由 enq 使用
     */
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    static final class Node {
        /** Marker to indicate a node is waiting in shared mode */
        static final Node SHARED = new Node();
        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;
        Node() {
        }

    }

    private transient volatile Node head;
    private volatile int state;

    public Node getHead() {
        return head;
    }

    public int getState() {
        return state;
    }

}
