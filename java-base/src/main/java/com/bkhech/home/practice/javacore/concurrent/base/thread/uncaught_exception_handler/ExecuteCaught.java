package com.bkhech.home.practice.javacore.concurrent.base.thread.uncaught_exception_handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author guowm
 * @date 2020/1/3
 * @description
 *
 * 参考：https://blog.csdn.net/u013256816/article/details/50417822
 */
public class ExecuteCaught {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            exec.execute(new ThreadPoolTask());
        }
        exec.shutdown();
    }


    static class ThreadPoolTask implements Runnable {
        @Override
        public void run() {
            // 放在线程内部可以捕获当前线程设置的UncaughtExceptionHandler逻辑
            Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(3 / 2);
            System.out.println(3 / 0);
            System.out.println(3 / 1);
        }

    }

    static class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t + "子线程发生异常： " + e);
        }
    }

}
