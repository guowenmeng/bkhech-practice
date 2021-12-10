package com.bkhech.home.practice.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStringUtil {

    private static final char[] ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z' };

    public static String getAlphaNumeric(int len) {
        char[] c = new char[len];
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }

    /**
     * Random random = new Random();//默认构造方法
     * Random random = new Random(1000);//指定种子数字
     *
     * 在进行随机时，随机算法的起源数字称为种子数(seed)，在种子数的基础上进行一定的变换，从而产生需要的随机数字。
     * 相同种子数的Random对象，相同次数生成的随机数字是完全相同的。也就是说，两个种子数相同的Random对象，第一次
     * 生成的随机数字完全相同，第二次生成的随机数字也完全相同。
     * 事实上，Random是一种伪随机数，相同的种子产生相同的序列
     */
    public static void random() {
        System.out.println("Math.random() = " + Math.random());
        ThreadLocalRandom current = ThreadLocalRandom.current();
        System.out.println("ThreadLocalRandom.current() = " + current.nextInt(0,10));

        Random r2 = new Random(50);//种子为50的对象
        Random r3 = new Random(50);//种子为50的对象
        //如果两个Random对象种子数相同，那么他们生成的结果将是一样。可以使用当前时间作为种子：System.currentTimeMillis()
        System.out.println("r2.nextInt():"+r2.nextInt(10)+"-------r3.nextInt():"+r3.nextInt(10));
    }

    public static void main(String[] args) {
        random();
    }


}
