package com.bkhech.home.practice.javacore.core.运算符;

/**
 * @author guowm
 * @date 2019/8/20
 * @description
 */
public class Operator {

    /**
     * ~（取反运算符）
     */
    private static void test() {
        int i = ~37;
        int j = ~-4;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }

    /**
     * 进制转换
     */
    public static void test1() {
        //128M
        long l = 1024 * 1024 * 128;
        String hexStr = Long.toHexString(l);
        //0x8000000
        System.out.println("hexStr = " + hexStr);
    }

    public static void main(String[] args) {
        test();
    }


}
