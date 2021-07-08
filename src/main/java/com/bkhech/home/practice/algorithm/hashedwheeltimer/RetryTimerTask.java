package com.bkhech.home.practice.algorithm.hashedwheeltimer;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *  在 {@link Timer#newTimeout(TimerTask, long, TimeUnit)}指定的延迟后执行的任务
 * @author guowm
 * @date 2021/7/7
 */
@Slf4j
public class RetryTimerTask implements TimerTask {
    private final Invocation invocation;
    private final int retries;
    private final long tick;
    private int retryTimes = 0;

    /**
     *
     * @param invocation
     * @param retries
     * @param tick unit: second
     */
    RetryTimerTask(Invocation invocation, int retries, long tick) {
        this.invocation = invocation;
        this.retries = retries;
        this.tick = tick;
    }

    @Override
    public void run(Timeout timeout) {
        try {
            invocation.getMethod().invoke(invocation.getTarget(), invocation.getParameterValues());
        } catch (Throwable e) {
            log.error("Failed retry to invoke method " + invocation.getMethod().getName() + ", waiting again.", e);
            if ((++retryTimes) >= retries) {
                log.error("Failed retry times exceed threshold (" + retries + "), We have to abandon, invoke method ->" + invocation.getMethod().getName());
            } else {
                rePut(timeout);
            }
        }
    }

    private void rePut(Timeout timeout) {
        if (timeout == null) {
            return;
        }

        Timer timer = timeout.timer();
        if (timer.isStop() || timeout.isCancelled()) {
            return;
        }

        timer.newTimeout(timeout.task(), tick, TimeUnit.SECONDS);
    }
}
