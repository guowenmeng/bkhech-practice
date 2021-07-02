package com.bkhech.home.practice;

import com.alibaba.fastjson.JSONObject;
import com.bkhech.home.practice.entity.AccountState;
import com.bkhech.home.practice.utils.DateTimeFormatterUtils;
import com.bkhech.home.practice.utils.RandomStringUtil;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author guowm
 * @date 2018/11/26
 */
public class BaseTests {

    /**
     * 串行情况下，没差。并行情况下，有差别。
     *
     * 第二行输出的一直是：
     * AAA
     * BBB
     * CCC
     * 而第一种输出的情况不确定。应为是并行处理。
     * 其实两者完成的功能类似，主要区别在并行处理上，forEach是并行处理的，forEachOrder是按顺序处理的，显然前者速度更快
     *
     * @see //  https://blog.csdn.net/zhangshk_/article/details/80773161
     */
    @Test
    public void forEachTest() {
        //1.串行
        IntStream.range(1, 20).forEach(i -> {
            Stream.of("AAA","BBB","CCC").forEach(s->System.out.println("Output forEach :"+s));
            Stream.of("AAA","BBB","CCC").forEachOrdered(s->System.out.println("Output forEachOrdered:"+s));
            System.out.println("=====================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //2.并行
        IntStream.range(1, 20).forEach(i -> {
            Stream.of("AAA","BBB","CCC").parallel().forEach(s->System.out.println("Output parallel forEach :"+s));
            Stream.of("AAA","BBB","CCC").parallel().forEachOrdered(s->System.out.println("Output parallel forEachOrdered:"+s));
            System.out.println("=====================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Map Test
      */
    @Test
    public void mapTest() {
        Map<Integer, Integer> map = new HashMap<>(5);

        IntStream.range(1,14).forEach(i -> {
            map.put(i,i);
            System.out.println("i = " + i);
            String a =  RandomStringUtil.getAlphaNumeric(9);
            System.out.println("map = " + a);
        });

    }
    
    @Test
    public void baseTest() {
        int h = "111111111111".hashCode();
        System.out.println(h);
        System.out.println("h = " + toBinaryAutoPadding32Bit(h));
        final int i = h >>> 16;
        System.out.println("i = " + toBinaryAutoPadding32Bit(i));
        int ret = h ^ i;
        System.out.println("r = " + toBinaryAutoPadding32Bit(ret));
    }

    private String toBinaryAutoPadding32Bit(int h) {
        final String binaryString = Integer.toBinaryString(h);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32 - binaryString.length(); i++) {
            sb.append("0");
        }
        sb.append(binaryString);
        return sb.toString();
    }

    /**
     * ThreadLocalRandom
     * @throws InterruptedException
     */
    @Test
    public void threadLocalRandomTest() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            int i = ThreadLocalRandom.current().nextInt(1, 7);
            System.out.println("i = " + i);
            atomicInteger.incrementAndGet();
            if (atomicInteger.get() ==20 ) {
                break;
            }
            Thread.sleep(1000);
        }
    }

    @Test
    public void localTimeTest(){

        int banHour = Integer.parseInt(" 2 小时 ".replace("小时", "").trim());

        String tenYearsLater = new Date().toInstant().atZone(ZoneId.systemDefault())
                .plusHours(banHour)
                .format(DateTimeFormatterUtils.LOCAL_DATE_TIME);
        System.out.println("tenYearsLater = " + tenYearsLater);

        AccountState accountState = new AccountState();

        JSONObject dataJson = new JSONObject();
        dataJson.put("banTime", System.currentTimeMillis()/1000 - 60 * 60);

        long banTime = dataJson.containsKey("banTime") ? dataJson.getLongValue("banTime") * 1000L : 0L;
        long chatBanTime = dataJson.containsKey("chatBanTime") ? dataJson.getLongValue("chatBanTime") * 1000L : 0L;
        if (banTime > System.currentTimeMillis()) {
            Date banDate = new Date(banTime);
            accountState.setBan(true);
            accountState.setBanTime(LocalDateTime.ofInstant(banDate.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatterUtils.LOCAL_DATE_TIME));
        }
        if (chatBanTime > System.currentTimeMillis()) {
            Date chatBanDate = new Date(chatBanTime);
            accountState.setChatBan(true);
            accountState.setChatBanTime(LocalDateTime.ofInstant(chatBanDate.toInstant(), ZoneId.systemDefault()).format(DateTimeFormatterUtils.LOCAL_DATE_TIME));
        }

        System.out.println("accountState = " + accountState);

    }


    @Test
    public void testMd5DigestAsHex() {
        String s = DigestUtils.md5DigestAsHex("key=23&ydt=37d".getBytes());
        System.out.println("s = " + s);
    }

    @Test
    public void unsigned() {
        long i = Integer.toUnsignedLong(-233);
        System.out.println("i = " + i);
        String s = Integer.toUnsignedString(-233);
        System.out.println("s = " + s);


        long a = 0xffffffffL;
        System.out.println("a = " + a);
        BigInteger bigInteger = new BigInteger("ffffffff", 16);
        System.out.println("bigInteger = " + bigInteger.toString());
        System.out.println("bigInteger = " + bigInteger.toString(2));
        System.out.println("bigInteger = " + bigInteger.toString(16));

        int cc = -12 >> 2;
        System.out.println("cc = " + cc);
        BigInteger integer = new BigInteger(String.valueOf(cc));
        String s1 = integer.toString(2);
        System.out.println("s1 = " + s1);

        BigInteger byteInteger = new BigInteger("00111111111111111111111111111101", 2);
        System.out.println("byteInteger = " + byteInteger.toString());

        /**
         * 取余。
         * 使用 & 来进行取余的算法比使用 / 效率高很多，
         * 虽然只能对2^n的数值进行取余计算，
         * 但是在JDK源码中也是经常被使用到，比如说HashMap中判断key在Hash桶中的位置。
         */
        int aMod = 33;
        int mod1 = aMod & 15;
        int mod2 = aMod % 16;
        System.out.println("mod1 = " + mod1);
        System.out.println("mod2 = " + mod2);

        /**
         * 求相反数
         */
        int aa = 1000;
        long bb = ~aa + 1;
        System.out.println("bb = " + bb);

        /**
         * 求绝对值
         */
        int aaa = -3000000;
        System.out.println("aaa >> 31 = " + (aaa >> 31));
        int bbb = aaa >> 31 == 0 ? aaa : (~aaa + 1);
        System.out.println("bbb = " + bbb);

        /**
         * 原文地址：https://blog.csdn.net/Leon_cx/article/details/81911183
         * 生成第一个大于或者等于a的满足2^n的数
         *
         * 这个标题可能显得不那么容易理解，下面结合场景来解释一下。
         *
         * 在HashMap中我们需要生成一个Hash桶，用来存储键值对（或者说存储链表）。
         * 当我们查询一个key的时候，会计算出这个key的hashCode，然后根据这个hashCode来计算出这个key在hash桶中的落点，
         * 由于上面介绍的使用 & 来取余效率比 % 效率高，所以HashMap中根据hashCode计算落点使用的是 & 来取余。
         * 使用 & 取余有一个局限性就是除数必须是2^n，所以hash桶的size必须是2^n。
         * 由于HashMap的构造器支持传入一个初始的hash桶size，所以HashMap需要对用户传入的size进行处理，生成一个第一个大于size的并且满足2^n的数。
         * 这个算法的使用场景介绍完毕了，那么再来看一下算法实现：
         *
         * 循环判断
         * public static final int tableSizeFor(int cap) {
         *     int size = 1;
         *     while (size < cap) {
         *         size *= 2;
         *     }
         *     return size;
         * }
         * | 运算符实现
         * public static final int tableSizeFor(int cap) {
         *     int n = cap - 1;
         *     n |= n >>> 1;
         *     n |= n >>> 2;
         *     n |= n >>> 4;
         *     n |= n >>> 8;
         *     n |= n >>> 16;
         *     return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
         * }
         * HashMap就是使用这个算法来修改用户使用构造器传进来的size的，这个算法是使用移位和或结合来实现的，性能上比循环判断要好。
         */
        Map map = new HashMap(2);
        Map concurrentMap = new ConcurrentHashMap(2);

        System.out.println("tableSizeFor() = " + tableSizeFor(6));

        int aaaa=232;
        //0000 0000 1110 1000

        System.out.println("aaaa = " + Integer.toBinaryString(aaaa));

        //强转变为8位：11101000（原码），十进制等于-24
        System.out.println("aaaa = " + (byte) aaaa);

        /**
         * 位溢出
         */
        byte dd = (byte)128;
        System.out.println("dd = " + dd);

    }
    int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println("n = " + n);
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}