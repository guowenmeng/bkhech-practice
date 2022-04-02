package com.bkhech.home.practice.javacore.core.reflection.method;

/**
 * A impl
 * @author guowm
 * @date 2022/1/17
 */
public class AClass implements SuperClass<String> {
    @Override
    public void method(String s) {
        System.out.println(s);
    }
}
