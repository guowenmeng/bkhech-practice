package com.bkhech.home.practice.javacore.concurrent.executors;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolExecutor workerCountOf() runStateOf() 解析
 * 高三位存储线程状态，剩余低位存储工作线程数量
 * {@literal https://www.icode9.com/content-1-562147.html}
 * @author guowm
 * @date 2021/9/10
 */
public class ThreadPoolExecutorWorkerCountOfDemo {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    public static void main(String[] args) {
        final ThreadPoolExecutorWorkerCountOfDemo test = new ThreadPoolExecutorWorkerCountOfDemo();
        System.out.println(test.ctl);
    }
}
