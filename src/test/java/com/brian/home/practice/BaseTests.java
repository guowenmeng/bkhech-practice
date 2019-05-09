package com.brian.home.practice;

import com.alibaba.fastjson.JSONObject;
import com.brian.home.practice.entity.AccountState;
import com.brian.home.practice.enums.PlayerHandlerType;
import com.brian.home.practice.utils.DateTimeFormatterUtils;
import com.brian.home.practice.utils.RandomStringUtil;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author guowm[guowm@5fun.com]
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
    public void EnumTest() {
        System.out.println(" = update something test....");
        System.out.println("PlayerHandlerType.BAN.getName() = " + PlayerHandlerType.BAN.getName());
        System.out.println(" = update something test....");
        System.out.println(" = update something test....");
        System.out.println(" = update something test....");
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



}
