package com.bkhech.home.practice.distributed.基本限流算法及思想.滑动窗口;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 限流方法（滑动时间算法/时间窗口算法）
 * @author guowm
 * @date 2021/6/29
 */
@Slf4j
public class WindowLimiter {
    /**
     * 一个时间窗口，单位是毫秒
     */
    private final long timeWindow;

    /**
     * 每个slot的时间段，单位是毫秒
     */
    private final long slotTime;

    /**
     * 一个时间窗口分割多少块（slot数量）
     */
    private final long slot;

    /**
     * 一个时间窗口限制的次数(在一个完整窗口期内允许通过的最大阈值)
     */
    private final long threshold;

    private final LoadingCache<Long, AtomicLong> counter;

    public WindowLimiter(int timeWindowSecond, int slotTime, int threshold) {
        // JVM 单线程初始化构造方法，保证绝对线程安全
//        if (counter == null) {
//            synchronized (this) {
//                if (counter == null) {
                    this.timeWindow = TimeUnit.SECONDS.toMillis(timeWindowSecond);
                    this.slotTime = slotTime;
                    this.slot = this.timeWindow / this.slotTime + (this.timeWindow % this.slotTime == 0 ? 0 : 1);
                    this.threshold = threshold;

                    counter = CacheBuilder.newBuilder()
                            .expireAfterWrite(slotTime, TimeUnit.MILLISECONDS)
                            .build(new CacheLoader<Long, AtomicLong>() {
                                @Override
                                public AtomicLong load(Long millis) throws Exception {
                                    return new AtomicLong(0L);
                                }
                            });
//                }
//            }
//        }
    }

    /**
     * @param currentTime 当前时间戳, 单位是毫秒
     * @return 是否限流, true 是
     */
    public boolean tryAcquire(long currentTime) {
        long key = currentTime / this.slotTime;
        try {
            long nums = 0;
            // slot windows timeWindowSecond
            for (long i = 0; i < slot; i = i+slotTime) {
                nums += counter.get(key - i).get();
            }
            log.info("key=" + key + ",nums=" + nums);
            if (nums > threshold) {
//                log.info("限流了,nums=" + nums);
                return false;
            }
            // 没达到阈值，则访问次数加1
            counter.get(key).incrementAndGet();
            return true;
        } catch (Exception e) {
            log.error("slideWindow error", e);
            return false;
        }
    }

    public static void main(String[] args) {
        final WindowLimiter windowLimiter = new WindowLimiter(4, 2000, 15);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {

                //每秒发送随机数量的请求
                int reqs = (int) (Math.random() * 5) + 1;
                final ConcurrentMap<Long, AtomicLong> longAtomicLongConcurrentMap = windowLimiter.counter.asMap();
                longAtomicLongConcurrentMap.forEach((key,value) -> {
                    System.out.println(String.join(":", "key", String.valueOf(key), ", value", value.toString()));
                });

                for (int i = 0; i < reqs; i++) {
                    windowLimiter.tryAcquire(System.currentTimeMillis());
                }
            } catch (Exception e) {
                log.error("slideWindow error", e);
            }
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }

}
