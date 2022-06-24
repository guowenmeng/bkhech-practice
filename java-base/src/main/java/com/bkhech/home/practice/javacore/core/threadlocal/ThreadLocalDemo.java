package com.bkhech.home.practice.javacore.core.threadlocal;

/**
 * ThreadLocal 样例
 * @author guowm
 * @date 2021/11/16
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("111");
        System.out.println("threadLocal.get() = " + threadLocal.get());

        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set("222");
        System.out.println("threadLocal2.get() = " + threadLocal2.get());


        System.out.println(threadLocal.get() + ":" + threadLocal2.get());
    }
}
