package com.bkhech.home.practice.designpattern.proxy.javaproxy;

/**
 * 具体主题
 * @author guowm
 * @date 2022/1/18
 */
public class RealSubject implements Subject {
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    public static void staticPrint(String message) {
        System.out.println("static " + message);
    }

}
