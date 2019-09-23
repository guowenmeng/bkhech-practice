package com.bkhech.home.practice.javacore.concurrent.base.synchronizeed;

import java.util.concurrent.TimeUnit;

/**
 * 运行结果是多少？
 *
 * 是小于1000的值。原因是对Integer count加锁，虽然Integer本身是一个对象，
 * 但是因为count的值是变化的，值的变化使得Integer的对象不断发生变化，
 * 也就是synchronized(count)这个对象并不是同一个。所以对于并发的场景来说，无法实现互斥
 *
 * count++会做装箱操作， Integer.valueOf(count) ->new Integer(count)
 */

public class SyncPit1 {

    static Integer count = 0;

    public static void incr() {
        synchronized (count) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
//          count++ => count = count + 1;
            System.out.println("count = " + count.hashCode());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> SyncPit1.incr()).start();
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("result = " + count);
    }

}
