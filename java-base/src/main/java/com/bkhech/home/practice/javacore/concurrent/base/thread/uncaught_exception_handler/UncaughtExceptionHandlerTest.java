package com.bkhech.home.practice.javacore.concurrent.base.thread.uncaught_exception_handler;

/**
 * @author guowm
 * @date 2020/1/2
 * @description 处理非正常的线程中止
 *
 * 当单线程的程序发生一个未捕获的异常时我们可以采用try....catch进行异常的捕获，
 * 但是在多线程环境中，线程抛出的异常是不能用try....catch捕获的，主线程获取不到子线程返回的异常，
 * 这样就有可能导致一些问题的出现，比如异常的时候无法回收一些系统资源，或者没有关闭当前的连接等等。
 *
 * Thread的run方法是不抛出任何检查型异常的，但是它自身却可能因为一个异常而被中止，导致这个线程的终结。
 */
public class UncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new Task());
            thread.start();
        } catch (Exception e) {
            System.out.println("==Exception: 代码执行不到这里: " + e.getMessage());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(3 / 2);
            System.out.println(3 / 0);
            System.out.println(3 / 1);
        }
    }

}
