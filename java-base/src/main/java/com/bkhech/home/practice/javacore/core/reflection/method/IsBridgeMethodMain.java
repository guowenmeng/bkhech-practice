package com.bkhech.home.practice.javacore.core.reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 桥接方法是 JDK 1.5 引入泛型后，为了使Java的泛型方法生成的字节码和 1.5 版本前的字节码相兼容，
 * 由编译器自动生成的方法。我们可以通过Method.isBridge()方法来判断一个方法是否是桥接方法。
 *
 * 1. 因为泛型是在1.5引入的，为了向前兼容，所以会在编译时去掉泛型（泛型擦除）。那么SuperClass接口中的method方法的参数在虚拟机中只能是Object.
 * 它应该是这个样子：
    *    public interface SuperClass {
    *        void method(Object  t);
    *    }
 *
 * 而 AClass 实现了SuperClass 接口，但是它的实现方法却是：
    *    public void method(String s) {
    * 　　　   System.out.println(s);
    * 　　}
 *
 * 根本就没有实现 void method(Object t) 方法。 这怎么回事，其实虚拟机自动实现了一个方法。
 * AClass在虚拟机中是这个样子：
    *   public class AClass implements SuperClass  {
    *       public void method(String s) {
    *           System.out.println(s);
    *       }
    *       public void method(Object s) {
    *            this.method((String) s);
    *       }
    *   }
 * 这个void method(Object s)  就是桥接方法。
 * 我们用这个命令查看：javap -p AClass.class
 * 显示如下：
    * Compiled from "AClass.java"
    * public class AClass implements SuperClass<java.lang.String> {
    *   public AClass();
    *   public void method(java.lang.String);
    *   public void method(java.lang.Object);
    * }
 * @see Method#isBridge()
 * @author guowm
 * @date 2022/1/17
 */
public class IsBridgeMethodMain {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AClass obj = new AClass();
        Method method = AClass.class.getMethod("method", String.class);
        method.invoke(obj, "asdf");
        System.out.println(method.isBridge());
        method = AClass.class.getMethod("method", Object.class);
        method.invoke(obj, "zxcv");
        System.out.println(method.isBridge());
        /**
         * Test result:
         * asdf
         * false
         * zxcv
         * true
         */
    }
}
