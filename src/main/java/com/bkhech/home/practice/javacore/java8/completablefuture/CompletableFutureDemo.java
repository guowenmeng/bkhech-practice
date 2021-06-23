package com.bkhech.home.practice.javacore.java8.completablefuture;

import java.util.concurrent.*;

/**
 * @author guowm
 * @date 2020/3/22
 * @description 
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo test = new CompletableFutureDemo();
        //一、自己设置future的result。
        // 创建一个带result的CompletableFuture
        /*CompletableFuture<String> future = CompletableFuture.completedFuture("result");
        String str = future.get();
        System.out.println("str = " + str);*/

        // 默认创建的CompletableFuture是没有result的，
        // 这时调用future.get()会一直阻塞下去知道有result或者出现异常
        /*future = new CompletableFuture<>();
        try {
            future.get(1, TimeUnit.SECONDS);
//            System.out.println(future.getNow("noResult"));
        } catch (Exception e) {
//           e.printStackTrace();
            System.out.println("e.toString() = " + e.toString());
        }*/

        // 给future填充一个result
        /*future.complete("result");
        System.out.println("future.get() = " + future.get());*/

        // 给future填充一个异常
        /*future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("exception"));
        try {
            future.get();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getCause().getMessage());
        }*/

        //二、上面的示例是自己设置future的result，
        // 一般情况下我们都是让其他线程或者线程池来执行future这些异步任务。
        // 除了直接创建CompletableFuture 对象外（不推荐这样使用），
        // 还可以使用如下4个方法创建CompletableFuture 对象：

        // 1.runAsync是Runnable任务，不带返回值的，如果入参有executor，则使用executor来执行异步任务
//        public static CompletableFuture<Void>  runAsync(Runnable runnable)
//        public static CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)

        // 2.supplyAsync是待返回结果的异步任务
//        public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier)
//        public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier, Executor executor)
        // 使用示例
        //注意：如果入参不带executor，则默认使用ForkJoinPool.commonPool()作为执行异步任务的线程池；否则使用executor执行任务。
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("hello world1");
        }, executorService);
        executorService.shutdown();
        CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world2");
            return "result";
        });*/

        //三、CompletableFuture的完成动作
//        public CompletableFuture<T>     whenComplete(BiConsumer<? super T,? super Throwable> action)
//        public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
//        public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
//        public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)

        // 使用示例
        CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world");
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
            }
            return "result";
        }).whenCompleteAsync((result, e) -> {
            System.out.println(result + " " + e);
        }).exceptionally(e -> {
            System.out.println("exception " + e);
            return "exception";
        });

        System.out.println("test end ====== ");

        new CountDownLatch(1).await();
    }

}
