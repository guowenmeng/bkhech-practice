package com.bkhech.home.practice.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author guowm
 * @date 2021/6/3
 */
public class CacheBuilderDemo {

    private final LoadingCache<String, AtomicLong> limiterCache;

    public CacheBuilderDemo() {
        limiterCache = CacheBuilder.newBuilder()
                .maximumSize(50)
                .weakValues()
                .expireAfterAccess(Duration.ofHours(1))
                .removalListener(notification -> System.out.println(notification.getValue()))
                .build(new CacheLoader<String, AtomicLong>() {
                    @Override
                    public AtomicLong load(String key) throws Exception {
                        return new AtomicLong();
                    }
                });
    }
}
