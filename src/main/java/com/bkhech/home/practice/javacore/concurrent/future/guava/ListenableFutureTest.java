package com.bkhech.home.practice.javacore.concurrent.future.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author guowm
 * @date 2019/9/3
 * @description
 */
public class ListenableFutureTest {

    public static void execute() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(6));

        ListenableFuture<String> future = executorService.submit(new Task());
        System.out.println("主任务执行完，开始异步执行副本任务.....");
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println(Thread.currentThread().getName() + "  result = " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(Thread.currentThread().getName() + " 失败！t = " + t);
            }
        }, executorService);

        System.out.println("回归主线任务");
    }

    public static void main(String[] args) {
        execute();
    }

}
