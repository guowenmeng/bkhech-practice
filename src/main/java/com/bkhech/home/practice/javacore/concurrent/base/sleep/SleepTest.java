package com.bkhech.home.practice.javacore.concurrent.base.sleep;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @author guowm
 * @date 2019/9/9
 * @description
 * quietlySleep 处理方式
 */
public class SleepTest {

    public static void quietlySleep(final long millis)
    {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            // I said be quiet!
            currentThread().interrupt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed = " + (endTime - startTime));
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> quietlySleep(1000 * 10));
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("mainThread sleep 5s ");

        thread.interrupt();

        System.out.println("mainThread end");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
