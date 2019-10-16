package com.bkhech.home.practice.javacore.core.值传递;

/**
 * @author guowm
 * @date 2019/10/16
 * @description java 只有值传递
 */
public class PassByValueTest {
    public static void main(String argv[ ]) {
        PassByValueTest t = new PassByValueTest( );
        t.first();
        //结果为：15 0 20
    }
    public void first( )
    {
        int i = 5;
        Value v = new Value( );
        v.i = 25;
        second(v,i);
        System.out.print(v.i);
    }
    public void second(Value v, int i)
    {
        i = 0;
        v.i = 20;
        Value val = new Value( );
        v = val;
        System.out.print(v.i+" "+i+" ");
    }
}

class Value {
    public int i = 15;
}