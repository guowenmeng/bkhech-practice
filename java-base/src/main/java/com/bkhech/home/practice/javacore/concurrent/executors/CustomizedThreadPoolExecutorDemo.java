package com.bkhech.home.practice.javacore.concurrent.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author guowm
 * @date 2021/2/25
 * @description ThreadPoolExecutor可以通过子类实现一些扩展功能。
 * <p>
 * protected void beforeExecute(Thread t, Runnable r);
 * protected void afterExecute(Runnable r, Throwable t);
 * protected void terminated()；
 * <p>
 * 这两个方法是在execute方法中执行线程前后执行，或者在线程terminated之前。子类可以实现这三个钩子(hook)方法，对线程池进行扩展。
 * <p>
 * 对beforeExecute方法进行重写，实现一个可暂停/恢复执行的线程池。
 */
public class CustomizedThreadPoolExecutorDemo {

    public static void main(String[] args) {
        new CustomizedThreadPoolExecutorDemo().simple();
    }

    private void simple() {
        int queueCapacity = 3, corePoolSize = 2, maximumPoolSize = 4;
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(queueCapacity);
        PausableThreadPoolExecutor threadPoolExecutor = new PausableThreadPoolExecutor(corePoolSize, maximumPoolSize, 10,
                TimeUnit.SECONDS, arrayBlockingQueue, new MyRejectedExecutionHandler());

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);

        timerPause(threadPoolExecutor, scheduledThreadPoolExecutor);
        timerResume(threadPoolExecutor, scheduledThreadPoolExecutor);
        for (int i = 1; i <= 10; i++) {
            final int tempI = i;
            Thread thread = new MyThreadFactory("customized-thread-pool")
                    .newThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("task" + tempI);
                        }
                    });
            threadPoolExecutor.execute(thread);
            System.out.printf("i:%d, queueSize:%d, poolSize:%d, coreSize:%d, maxSize:%d，largestPoolSize:%d， activeCount:%s\n",
                    tempI, arrayBlockingQueue.size(), threadPoolExecutor.getPoolSize(),
                    threadPoolExecutor.getCorePoolSize(), threadPoolExecutor.getMaximumPoolSize(),
                    threadPoolExecutor.getLargestPoolSize(), threadPoolExecutor.getActiveCount());
        }

        threadPoolExecutor.shutdown();
        scheduledThreadPoolExecutor.shutdown();
        while (true) {
            if (threadPoolExecutor.isTerminated()) {
                System.out.println("over");
                break;
            }
        }
        System.out.println();
    }

    public static void timerPause(PausableThreadPoolExecutor executor, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> executor.pause(), 10000L, 10000L, TimeUnit.MILLISECONDS);
    }

    public static void timerResume(PausableThreadPoolExecutor executor, ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> executor.resume(), 10000L, 15000L, TimeUnit.MILLISECONDS);
    }

    class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("MyRejectedExecutionHandler");
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

    public class PausableThreadPoolExecutor extends ThreadPoolExecutor {

        public PausableThreadPoolExecutor(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public PausableThreadPoolExecutor(int corePoolSize,
                                          int maximumPoolSize,
                                          long keepAliveTime,
                                          TimeUnit unit,
                                          BlockingQueue<Runnable> workQueue,
                                          RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        volatile boolean isPause;
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        @Override
        public void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            lock.lock();
            try {
                while (isPause) {
                    long ms = 10L;
                    System.out.printf("pausing, %s\n", t.getName());
                    Thread.sleep(ms);
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 暂停
         */
        public void pause() {
            lock.lock();
            try {
                System.out.println("exe pause");
                isPause = true;
            } finally {
                lock.unlock();
            }
        }

        /**
         * 继续执行
         */
        public void resume() {
            lock.lock();
            try {
                System.out.println("un pause");
                isPause = false;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


}
