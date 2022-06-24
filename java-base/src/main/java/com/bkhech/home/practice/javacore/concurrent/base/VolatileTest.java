package com.bkhech.home.practice.javacore.concurrent.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guowm
 * @date 2020/1/6
 * @description
 */
public class VolatileTest {

//    static volatile int a = 1;
//    static volatile int b = 1;
    static int a = 1;
    static int b = 1;

    public static AtomicInteger aa = new AtomicInteger(0);
    public static AtomicInteger bb = new AtomicInteger(0);
    public static AtomicInteger ab = new AtomicInteger(0);

    static synchronized void add() {
        a++;
        b++;
    }

    static synchronized void print() {
        if (a > b) {
            aa.getAndIncrement();
        } else if (a < b) {
            bb.getAndIncrement();
        } else if (a == b) {
            ab.getAndIncrement();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 100000; i++) {

            new Thread(() -> {
                add();
                print();
            }).start();
        }

        System.out.println("a>b: " + aa);
        System.out.println("b>a: " + bb);
        System.out.println("a==b: " + ab);

    }

}
