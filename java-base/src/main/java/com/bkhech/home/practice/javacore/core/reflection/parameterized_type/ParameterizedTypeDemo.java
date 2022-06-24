package com.bkhech.home.practice.javacore.core.reflection.parameterized_type;
/**
 * 泛型参数化样例
 * @author guowm
 * @date 2021/7/6
 */
public class ParameterizedTypeDemo extends ParameterizedClass<MyClass, MyInvoke> implements ParameterizedInterface<String, String> {
    public static void main(String[] args) {
        ParameterizedTypeDemo parameterizedTypeDemo = new ParameterizedTypeDemo();
    }

}
