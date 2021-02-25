package com.bkhech.home.practice.javacore.core;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guowm
 * @date 2021/2/24
 * @description SimpleDateFormat线程不安全原因及解决方案
 * <p>
 * ps：https://www.cnblogs.com/fswhq/p/9867644.html
 * <p>
 * 如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat，
 * 官方给出的解释：simple beautiful strong immutable thread-safe。
 */
public class SimpleDateFormatTest {
    //线程不安全
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 解决：线程安全
     */
    private static final ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("SimpleDateFormatTest");
            return thread;
        }
    });

    final Integer MAX_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        new SimpleDateFormatTest().test(count);

        TimeUnit.MINUTES.sleep(1);
    }

    public void test(AtomicInteger count) {

        while (count.getAndIncrement() < MAX_COUNT) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = SimpleDateFormatTest.simpleDateFormat.get();

                    String dateString = simpleDateFormat.format(new Date());
                    try {
                        Date parseDate = simpleDateFormat.parse(dateString);
                        String dateString2 = simpleDateFormat.format(parseDate);
                        System.out.println(dateString.equals(dateString2));
                    } catch (ParseException e) {
//                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
