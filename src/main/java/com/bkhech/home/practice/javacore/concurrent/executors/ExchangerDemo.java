package com.bkhech.home.practice.javacore.concurrent.executors;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guowm
 * @date 2021/2/26
 * @description Exchanger（交换者）是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交
 * 换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。当两个线程都到达同步点时，
 * 这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 * <p>
 * 如果两个线程有一个没有执行exchange()方法，则会一直等待，如果担心有特殊情况发
 * 生，避免一直等待，可以使用exchange（V x，longtimeout，TimeUnit unit）设置最大等待时长。
 */
public class ExchangerDemo {

    /**
     * java7 钻石语法
     */
    private static final Exchanger<String> exchanger = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A"; // A录入银行流水数据
                    String exchange = exchanger.exchange(A);
                    System.out.println("exchange : " + exchange);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B"; // B录入银行流水数据
                    String exchange = exchanger.exchange("B");
                    System.out.println("A和B数据是否一致：" + exchange.equals(B) + "，A录入的是："
                            + exchange + "，B录入是：" + B);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.shutdown();
    }
}



