package com.bkhech.home.practice.designpattern.proxy.cglib;

/**
 * @author guowm
 * @date 2020/12/18
 * @description FastClass机制：这里，tt可以看作目标对象，fc可以看作是代理对象；首先根据代理对象的getIndex方法获取目标方法的索引，
 * 然后再调用代理对象的invoke方法就可以直接调用目标类的方法，避免了反射
 */
public class FastClassTest {
    public static void main(String[] args) {
        Test tt = new Test();
        Test2 fc = new Test2();
        int index = fc.getIndex("f()V");
        fc.invoke(index, tt, null);
    }
}

class Test {
    public void f() {
        System.out.println("f method");
    }

    public void g() {
        System.out.println("g method");
    }
}

class Test2 {
    public Object invoke(int index, Object o, Object[] ol) {
        Test t = (Test) o;
        switch (index) {
            case 1:
                t.f();
                return null;
            case 2:
                t.g();
                return null;
        }
        return null;
    }

    //这个方法对Test类中的方法建立索引
    public int getIndex(String signature) {
        switch (signature.hashCode()) {
            case 3078479:
                return 1;
            case 3108270:
                return 2;
        }
        return -1;
    }
}