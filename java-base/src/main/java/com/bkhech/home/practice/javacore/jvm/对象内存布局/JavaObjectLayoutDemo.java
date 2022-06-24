package com.bkhech.home.practice.javacore.jvm.对象内存布局;

import org.openjdk.jol.info.ClassLayout;

/**
 * 分析Java对象的内存布局示例
 *
 * @author guowm
 * @date 2022/2/17
 */
public class JavaObjectLayoutDemo {
    public static void main(String[] args) {
        Car car = new Car(1, "SUV", 'A', 22.22);
        System.out.println(Integer.toHexString(car.hashCode()));
        System.out.println(ClassLayout.parseInstance(car).toPrintable());
        System.out.println("=====================================================");
        int[] array = new int[3];
        array[0] = 11;
        array[1] = 22;
        array[2] = 33;
        System.out.println(ClassLayout.parseInstance(array).toPrintable());
    }
}
