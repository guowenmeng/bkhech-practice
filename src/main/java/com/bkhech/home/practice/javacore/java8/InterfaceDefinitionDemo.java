package com.bkhech.home.practice.javacore.java8;

/**
 * java8 接口支持的新的定义方法
 * @author guowm
 * @date 2021/9/17
 */
public interface InterfaceDefinitionDemo {
    /**
     * 普通方法（对象方法）
     */
    void a();

    /**
     * 默认方法
     */
    default void b() {

    }
    default void f() {

    }

    /**
     * 类方法（共享方法）
     */
    static void c() {

    }
    static void d() {

    }
}
