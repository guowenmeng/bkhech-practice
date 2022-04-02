package com.bkhech.home.practice.utils;

import com.google.common.base.Equivalence;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义 GuavaCache
 *
 * @author guowm
 * @date 2021/11/22
 */
public class GuavaCacheUtil {
    /**
     * 日志器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(GuavaCacheUtil.class);

    private final static GuavaCacheUtil INSTANCE = new GuavaCacheUtil();

    private final Cache<String, Object> cache;

    //可从配置文件读取
    private final Long maximumSize = 1000000L;

    //可从配置文件读取
    private final Integer concurrencyLevel = 8;

    // key: value = 要存储的缓存键值： 该键值过期时间（秒）
    private final ConcurrentHashMap<String, Long>[] expireSegments;

    private static final Equivalence<Object> keyEquivalence = Equivalence.equals();

    private final int segmentShift;

    private final int segmentMask;

    public static void main(String[] args) {
        final GuavaCacheUtil guavaCacheUtil = GuavaCacheUtil.getInstance();
        for (int i = 0; i < 30; i++) {
            guavaCacheUtil.set(String.valueOf(i + 1), i + 1, 300);
        }
        final Object o = guavaCacheUtil.get("3");
        System.out.println(o);
        final Long del = guavaCacheUtil.del("3");
        System.out.println(del);
        final Object o1 = guavaCacheUtil.get("3");
        System.out.println(o1);
    }

    private GuavaCacheUtil() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .concurrencyLevel(concurrencyLevel)
                .recordStats()
                .build();
        int segmentShift = 0;
        int segmentCount = 1;
        while (segmentCount < concurrencyLevel) {
            ++segmentShift;
            segmentCount <<= 1;
        }
        this.segmentShift = 32 - segmentShift;
        this.segmentMask = segmentCount - 1;
        this.expireSegments = new ConcurrentHashMap[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            expireSegments[i] = new ConcurrentHashMap<>();
            new Terminator(expireSegments[i]).start();
        }
        new Doctor().start();
    }

    public static GuavaCacheUtil getInstance() {
        return INSTANCE;
    }

    /**
     * 缓存查询
     *
     * @param key 要查询的缓存键
     * @return 查询到的缓存值
     */
    public <T> T get(String key) {
        T res = (T) cache.getIfPresent(key);
        return res;
    }

    /**
     * 向缓存中添加缓存项
     *
     * @param key     要存储的缓存键
     * @param value   要存储的缓存值
     * @param seconds 缓存要存储的时间(<=0永不失效)
     * @return OK:设置成功 ERR:设置失败
     */
    public String set(final String key, Object value, Integer seconds) {
        return set(key, value, seconds, false);
    }

    /**
     * 向缓存中添加缓存项
     *
     * @param key            要存储的缓存键
     * @param value          要存储的缓存值
     * @param seconds        缓存要存储的时间(<=0永不失效)
     * @param sync2OtherNode 将变化同步到其他实例节点，true:同步;false:不同步
     * @return OK:设置成功 ERR:设置失败
     */
    public String set(final String key, Object value, Integer seconds, Boolean sync2OtherNode) {
        return _set(key, value, seconds);
    }

    /**
     * 向缓存中添加缓存项
     *
     * @param key     要存储的缓存键
     * @param value   要存储的缓存值
     * @param seconds 缓存要存储的时间(<=0永不失效)
     * @return OK:设置成功 ERR:设置失败
     */
    private String _set(final String key, Object value, Integer seconds) {
        try {
            if (seconds > 0) {
                int hash = hash(key);
                segmentFor(hash).put(key, System.currentTimeMillis() + seconds * 1000);
            }
            cache.put(key, value);
            return "OK";
        } catch (Exception e) {
            LOGGER.error("put object into cache fired,key:{},value:{}", key, value);
            return "ERR";
        }
    }

    /**
     * 删除缓存
     *
     * @param key 要删除的缓存键
     * @return 成功删除缓存记录数，如果缓存不存在返回值为0
     */
    public Long del(final String key) {
        return del(key, false);
    }

    /**
     * 删除缓存
     *
     * @param key            要删除的缓存键
     * @param sync2OtherNode 将变化同步到其他实例节点，true:同步;false:不同步
     * @return 成功删除缓存记录数，如果缓存不存在返回值为0
     */
    public Long del(final String key, boolean sync2OtherNode) {
        return _del(key);
    }

    /**
     * 删除缓存
     *
     * @param key 要删除的缓存键
     * @return 成功删除缓存记录数，如果缓存不存在返回值为0
     */
    private Long _del(final String key) {
        cache.invalidate(key);
        LOGGER.warn("delete object from cache,key:{}", key);
        return 1L;
    }

    private int hash(Object key) {
        int h = keyEquivalence.hash(key);
        return rehash(h);
    }

    /**
     * 目的：
     *  进一步降低 hash 冲突的概率，具体啥值不重要
     * @param h
     * @return
     */
    private int rehash(int h) {
        h += (h << 15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h << 3);
        h ^= (h >>> 6);
        h += (h << 2) + (h << 14);
        return h ^ (h >>> 16);
    }

    private ConcurrentHashMap<String, Long> segmentFor(int hash) {
        return expireSegments[(hash >>> segmentShift) & segmentMask];
    }

    /**
     * 缓存过期项清理
     */
    class Terminator extends Thread {

        private final ConcurrentHashMap<String, Long> expireMap;

        public Terminator(ConcurrentHashMap<String, Long> expireMap) {
            this.expireMap = expireMap;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Set<Map.Entry<String, Long>> entrySet = expireMap.entrySet();
                    Iterator<Map.Entry<String, Long>> it = entrySet.iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Long> entry = it.next();
                        if (entry.getValue() <= System.currentTimeMillis()) {
                            cache.invalidate(entry.getKey());
                            expireMap.remove(entry.getKey());
                            LOGGER.debug("key->" + entry.getKey() + "过期清理成功");
                        }
                    }
                    int clearInterval = 10;  //可从配置文件读取
                    Thread.sleep(clearInterval * 1000);
                } catch (Exception e) {
                    LOGGER.error("LocalCacheUtil Terminator执行失败", e);
                }
            }
        }
    }

    /**
     * 本地缓存自检程序
     */
    class Doctor extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    long cacheSize = cache.size();
                    CacheStats cacheStats = cache.stats();
                    String cacheInfo = "Local cache self check information,stats->" + cacheStats.toString() + ",cache size->" + cacheSize;
                    LOGGER.info(cacheInfo);
                    int sizeThreshold = 80;   //可从配置文件读取
                    if (((double) cacheSize / maximumSize) * 100 >= sizeThreshold) {
                        cacheInfo = "本地缓存使用率过高，最大缓存量：" + maximumSize + "，当前缓存量：" + cacheSize;
                        LOGGER.warn(cacheInfo);
                    }
                    int selfCheckInterval = 10;
                    Thread.sleep(selfCheckInterval * 1000);
                } catch (Exception e) {
                    LOGGER.error("LocalCacheUtil Doctor execute error", e);
                }
            }
        }
    }

}
