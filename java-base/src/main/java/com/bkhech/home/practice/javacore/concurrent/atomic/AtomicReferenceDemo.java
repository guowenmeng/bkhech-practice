package com.bkhech.home.practice.javacore.concurrent.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author guowm
 * @date 2020/9/3
 * @see AtomicReference
 * @see java.util.concurrent.atomic.AtomicReferenceFieldUpdater
 * @see java.util.concurrent.atomic.AtomicReferenceArray
 * @see java.util.concurrent.atomic.AtomicStampedReference
 * @see java.util.concurrent.atomic.AtomicMarkableReference
 * @description
 */
public class AtomicReferenceDemo {

    @Data
    @AllArgsConstructor
    private static class Value {
        private int value;
        private String str;
    }

    public static void main(String[] args) {
//        atomicStringReference();

        atomicObjectReference();
    }

    static AtomicReference<Value> atomicObjectReference = new AtomicReference<Value>(new Value(1, "initial value referenced"));

    /**
     * 原子引用对象
     */
    private static void atomicObjectReference() {
        Value newReferenceValue = new Value(2, "new value referenced");

        Value initialReferenceValue = atomicObjectReference.get();

        /**
         * note: 要想改变atomicObjectReference中的Value值，只能通过compareAndSet()方法
         * 不论中间怎么改变AtomicReference引用的value对象，都无效。
         * 从而体现了，原子性的改变一个对象的多个属性值。
         */
        //1.不论中间怎么改变AtomicReference引用的value对象，都无效。
        initialReferenceValue.setValue(3);
        initialReferenceValue = new Value(555, "555");

        //2.只能通过这个方法(compareAndSet)改变才有效
        boolean exchanged = atomicObjectReference.compareAndSet(initialReferenceValue, newReferenceValue);

        //3.最终通过返回的布尔值判断，是否原子性的更改成功
        System.out.println("exchanged: " + exchanged);

        initialReferenceValue = atomicObjectReference.get();
        System.out.println("updatedValue = " + initialReferenceValue);

    }

    /**
     * 原子引用字符串，其实也是对象
     */
    private static void atomicStringReference() {
        String initialReference = "initial value referenced";

        AtomicReference<String> atomicStringReference =
                new AtomicReference<String>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        String updatedValue = atomicStringReference.get();
        System.out.println("updatedValue = " + updatedValue);


        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged2: " + exchanged);

        updatedValue = atomicStringReference.get();
        System.out.println("updatedValue2 = " + updatedValue);
    }

}
