package com.bkhech.home.practice.javacore.concurrent.future.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author guowm
 * @date 2019/9/3
 * @description 任务类
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("子任务" + Thread.currentThread().getName() + " starting ... ");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("子任务" + Thread.currentThread().getName() + " already done ... ");
//        int i = 1/0;
        return "success";
    }
}
