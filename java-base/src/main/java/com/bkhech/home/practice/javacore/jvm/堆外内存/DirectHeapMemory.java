package com.bkhech.home.practice.javacore.jvm.堆外内存;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @author guowm
 * @date 2020/1/6
 * @description
 *
 *  java 申请堆外内存
 *   sun.misc.Unsafe 和 DirectByteBuffer
 *  参考：https://www.jianshu.com/p/17e72bb01bf1
 *
 */
public class DirectHeapMemory {

    public static void main(String[] args) throws Exception {
//        unsafeMethod();
        directByteBufferMethod();
    }

    /**
     * sun.misc.Unsafe
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void unsafeMethod() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        long a= unsafe.allocateMemory(1024);
        long b= unsafe.allocateMemory(2048);
        unsafe.reallocateMemory(a, 1024);
        unsafe.reallocateMemory(b, 1024);
        unsafe.freeMemory(a);
        unsafe.freeMemory(b);
    }

    /**
     * java.nio.DirectByteBuffer
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void directByteBufferMethod() throws NoSuchFieldException, IllegalAccessException {
        //申请1kb内存。其内部使用的还是 sun.misc.Unsafe。由JVM决定在合适的时候自动释放内存。
        //分配一块1024Bytes的堆外内存(直接内存)
        //allocateDirect方法内部调用的是DirectByteBuffer
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
        //向堆外内存中读写数据
        System.out.println(buffer.capacity());
        buffer.putInt(0,2018);
        System.out.println(buffer.getInt(0));
    }

}
