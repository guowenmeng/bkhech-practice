package com.bkhech.home.practice.javacore.concurrent.executors;

import java.util.concurrent.CyclicBarrier;

/**
 * @author guowm
 * @date 2021/2/26
 * @description
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
//        CyclicBarrierTest1.invoke();
        CyclicBarrierTest2.invoke();
    }

    /**
     * 因为主线程和子线程的调度是由CPU决定的，两个线程都有可能先执行，所以会产生两种输出
     */
    static class CyclicBarrierTest2 {
        static CyclicBarrier c = new CyclicBarrier(2, new CyclicBarrierDemo.CyclicBarrierTest2.A());

        public static void invoke() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        c.await();
                    } catch (Exception e) {
                    }
                    System.out.println(1);
                }
            }).start();
            try {
                c.await();
            } catch (Exception e) {
            }
            System.out.println(2);
        }

        static class A implements Runnable {
            @Override
            public void run() {
                System.out.println(3);
            }
        }
    }

    /**
     * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrierAction），
     * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
     */
    static class CyclicBarrierTest1 {
        static CyclicBarrier c = new CyclicBarrier(2);
        public static void invoke() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        c.await();
                    } catch (Exception e) {
                    }
                    System.out.println(1);
                }
            }).start();
            try {
                c.await();
            } catch (Exception e) {
            }
            System.out.println(2);
        }
    }
}
