package com.bkhech.home.practice.javacore.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntUnaryOperator;

/**
 * @author guowm
 * @date 2021/2/26
 * @description 需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
 * 复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组。
 */
public class AtomicIntegerArrayDemo {

    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        /**
         * Unary 一元组操作
         */
        ai.getAndUpdate(0, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                System.out.println("operand = " + operand);
                return 3;
            }
        });

        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);

    }

}
