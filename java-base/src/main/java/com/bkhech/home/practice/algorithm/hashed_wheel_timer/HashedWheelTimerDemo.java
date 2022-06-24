package com.bkhech.home.practice.algorithm.hashed_wheel_timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 时间轮样例
 * 参考：{@literal https://www.sohu.com/a/344587119_355142}
 * @author guowm
 * @date 2021/9/28
 */
public class HashedWheelTimerDemo {
    private static final HashedWheelTimer timer = new HashedWheelTimer();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task1");
                Thread.sleep(5000);
                System.out.println("task1 end");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("task2");
            }
        };

        timer.newTimeout(task1, 2, TimeUnit.SECONDS);
        timer.newTimeout(task2, 4, TimeUnit.SECONDS);
        new CountDownLatch(1).await();
    }

}
