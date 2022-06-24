package com.bkhech.home.practice.designpattern.proxy.javaproxy;

/**
 * jdk dynamic proxy entry class
 * @author guowm
 * @date 2022/1/18
 */
public class Main {
    public static void main(String[] args) {
        RealSubject target = new RealSubject();
        final JdkDynamicProxy<Subject> jdkDynamicProxy = new JdkDynamicProxy<>(target);
        final Subject proxy = jdkDynamicProxy.getProxy(Subject.class);
        System.out.println("jdk dynamic proxy ");
        proxy.print("hello world");
    }
}
