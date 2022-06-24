package com.bkhech.home.practice.javacore.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java对引用的分类
 *
 * {@literal https://www.processon.com/diagraming/61ee85f1f346fb06e1545a63}
 *
 * @author guowm
 * @date 2022/1/24
 */
public class ReferenceTest {

    public static void main(String[] args) {
//        strongReferenceTest();
//        softReferenceTest();
//        weakReferenceTest();
        phantomReferenceTest();
    }

    /**
     * 强引用
     * 即使显式调用了垃圾回收，但是用于date是强引用，date没有被回收。
     */
    public static void strongReferenceTest() {
        MyDate date = new MyDate();
        System.gc();
    }

    /**
     * 软引用
     * 在内存不足时，软引用被终止。
     * <p>
     * 此段代码等价于
     * MyDate date = new MyDate();
     * // 由JVM决定运行
     * If(JVM.内存不足()) {
     * date = null;
     * System.gc();
     * }
     */
    public static void softReferenceTest() {
        SoftReference ref = new SoftReference(new MyDate());
        for (int i = 0; i < 80; i++) {
            DrainMemory.drainMemory();
        }
    }

    /**
     * 弱引用
     * 在JVM垃圾回收运行时，弱引用被终止.
     * <p>
     * 等同于：
     * MyDate date = new MyDate();
     * // 垃圾回收
     * If(JVM.垃圾回收()) {
     * date = null;
     * System.gc();
     * }
     */
    public static void weakReferenceTest() {
        WeakReference ref = new WeakReference(new MyDate());
        System.gc();
    }

    /**
     * 假象引用 phantom /ˈfæntəm/
     * 假象引用，在实例化后，就被终止了。
     */
    public static void phantomReferenceTest() {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.gc();
    }

}
