package com.bkhech.home.practice.javacore.core.long_of_cache;

/**
 * Long缓存机制 LongCache -127~128
 * Integer同理
 *
 * @author guowm
 * @date 2021/3/3
 */
public class LongDemo {
    public static void main(String[] args) {
        Long s1 = Long.parseLong("127");
        Long s2 = Long.parseLong("127");
        // true
        System.out.println(s1 == s2);

        Long s3 = Long.parseLong("129");
        Long s4 = Long.parseLong("129");
        // false
        System.out.println(s3 == s4);

    }
}
