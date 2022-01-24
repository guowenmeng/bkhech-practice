package com.bkhech.home.practice.javacore.jvm.reference;

/**
 * 消耗大量内存，以此来引发JVM回收内存
 * @author guowm
 * @date 2022/1/24
 */
public class DrainMemory {
    public static void drainMemory() {
        String[] array = new String[1024 * 20];
        for (int i = 0; i < 1024 * 20; i++) {
            for (int j = 'a'; j < 'z'; j++) {
                array[i] += (char) j;
            }
        }
    }
}
