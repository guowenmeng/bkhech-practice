package com.bkhech.home.practice.javacore.jvm.reference;

/**
 * 首先限制内存大小：设置-Xms5m -Xmx5m
 * JVM什么时候进行垃圾回收？
 * 总结：JVM的垃圾回收机制，在内存充足的情况下，除非你显式调用System.gc()，否则它不会进行垃圾回收；在内存不足的情况下，垃圾回收将自动运行
 * @author guowm
 * @date 2022/1/24
 */
public class WhenJVMDoGcTest {

    public static void main(String[] args) {
//        noGarbageRetrieve();
//        explicitGarbageRetrieve();
        implicitGarbageRetrieve();
    }

    /**
     * 清除对象
     * date虽然设为null，但由于JVM没有执行垃圾回收操作，MyDate的finalize()方法没有被运行。
     */
    public static void noGarbageRetrieve() {
        MyDate date = new MyDate();
        date = null;
    }

    /**
     * 显式调用垃圾回收
     * 调用了System.gc()，使JVM运行垃圾回收，MyDate的finalize()方法被运行。
     */
    public static void explicitGarbageRetrieve () {
        MyDate date = new MyDate();
        date = null;
        System.gc();
    }

    /**
     * 隐式调用垃圾回收
     * 虽然没有显式调用垃圾回收方法System.gc()，但是由于运行了耗费大量内存的方法，触发JVM进行垃圾回收。
     */
    public static void implicitGarbageRetrieve () {
        MyDate date = new MyDate();
        date = null;
        DrainMemory.drainMemory();
    }

}
