package com.bkhech.home.practice.javacore.java8;

import java.util.Arrays;

/**
 * @author guowm
 * @date 2021/3/1
 * @description Stream调试
 * https://blog.csdn.net/doctor_who2004/article/details/82872175
 */
public class DebugDemo {

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 45).stream()
                .filter(i -> i % 2 == 0 || i % 3 == 0)
                .map(i -> i * i)
                .forEach(System.out::print);
    }

}
