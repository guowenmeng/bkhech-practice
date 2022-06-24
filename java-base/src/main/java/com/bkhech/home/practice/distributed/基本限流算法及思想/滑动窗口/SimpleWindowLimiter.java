package com.bkhech.home.practice.distributed.基本限流算法及思想.滑动窗口;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 本文代码逻辑：新建一个本地缓存，每5s为一个时间窗口，每1s为一个时间片段，
 * 时间片段作为缓存的key，原子类计数器作为缓存的value。
 * 每秒发送随机数量的请求，计算每个时间片段的前5秒内的累加请求数量，超出阈值则限流。
 * @author guowm
 * @date 2021/6/3
 * @see {@literal https://blog.csdn.net/king0406/article/details/103129786}
 */
@Slf4j
public class SimpleWindowLimiter {

    private final LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, AtomicLong>() {
                @Override
                public AtomicLong load(Long seconds) throws Exception {
                    return new AtomicLong(0L);
                }
            });

    /**
     * 限流方法（滑动时间算法/时间窗口算法）
     * @param key 限流标识
     * @param timeWindowSecond 一个时间窗口（单位：秒）
     * @param threshold 一个时间窗口限制的最大次数 (限流阈值)
     * @return 是否限流, true 是
     */
    public boolean slideWindow(Long key, int timeWindowSecond, int threshold) {
        try {
            long nums = 0;
            // timeSecond windows period s
            for (int i = 0; i < timeWindowSecond; i++) {
                nums += counter.get(key - i).get();
            }
            log.info("timeSecond=" + key + ",nums=" + nums);
            if (nums > threshold) {
                log.info("限流了,nums=" + nums);
            }
            return true;
        } catch (Exception e) {
            log.error("slideWindow error", e);
            return false;
        }
    }

    public static void main(String[] args) {
        final SimpleWindowLimiter windowLimiter = new SimpleWindowLimiter();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                // 当前时间戳
                long timeMillis = System.currentTimeMillis() / 1000;

                //每秒发送随机数量的请求
                int reqs = (int) (Math.random() * 5) + 1;
                windowLimiter.counter.get(timeMillis).addAndGet(reqs);

                final boolean ret = windowLimiter.slideWindow(timeMillis, 5, 15);
                System.out.println(ret);
            } catch (Exception e) {
                log.error("slideWindow error", e);
            }
        }, 5000, 1000, TimeUnit.MILLISECONDS);
    }

}
