package com.bkhech.home.practice.algorithm.hashedwheeltimer;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 时间轮样例
 * @author guowm
 * @date 2021/7/7
 */
public class HashedWheelTimerDemo {
    public static void main(String[] args) throws NoSuchMethodException, IOException {
        int maxFailBackTasks = 1000;
        final CustomizableThreadFactory customizableThreadFactory = new CustomizableThreadFactory("failback-cluster-timer");
        customizableThreadFactory.setDaemon(true);
        HashedWheelTimer failTimer = new HashedWheelTimer(
                customizableThreadFactory, 1,
                TimeUnit.SECONDS, 3, maxFailBackTasks);

        for (int i = 0; i < 500; i++) {
            Invocation invocation = new Invocation(new HashedWheelTimerDemo(), HashedWheelTimerDemo.class.getMethod("executeMethod", String.class), new String[]{"hello method"});
            final RetryTimerTask retryTimerTask = new RetryTimerTask(invocation, 3, 1);
            failTimer.newTimeout(retryTimerTask, 1, TimeUnit.SECONDS);
        }
        System.in.read();
    }

    public String executeMethod(String methodName) {
        System.out.println("执行了方法：" + methodName);
        int i = 1/0;
        return methodName;
    }
}
