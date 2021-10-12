package com.bkhech.home.practice.javacore.java8.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Java CompletableFuture实现CompletionStage和Future接口
 * @author guowm
 * @date 2020/3/22
 * @description
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 一、CompleteFuture 基本使用方式样例
//        testBasicCompleteFutureUsage();

        // 二、创建CompleteFuture方式
//        testCreateCompleteFuture();

        // 三、CompletableFuture的完成动作
//        testCompleteAction();

        // 四、CompletableFuture：多个 CompletableFuture 组合，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序
//        testMultiplyCompleteStage();

        // 五、阻塞获取结果
//        testBlockGetResult();

        // 六、acceptEither使用样例
//        testAcceptEither();

        // 七、Function、Consumer使用样例
        testFunctionConsumer();
    }

    /**
     * 一、CompleteFuture 基本使用方式样例
     */
    private static void testBasicCompleteFutureUsage() {
        //一、自己设置future的result。
        // 创建一个带result的CompletableFuture
        CompletableFuture<String> future = CompletableFuture.completedFuture("result");
        // 阻塞获取结果的方式有：join() 或者 get()

        //1. join()阻塞获取结果：该方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出，
        /*final String joinRet = future.join();
        System.out.println(joinRet);*/

        //2. get()阻塞获取结果
        try {
            String getRet = future.get();
            System.out.println(getRet);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 3.默认创建的CompletableFuture是没有result的，这时调用future.get()会一直阻塞下去直到有result或者出现异常
        future = new CompletableFuture<>();
        /*try {
            // 等待 1s 获取结果，否则抛出 TimeoutException
//            System.out.println(future.get(1, TimeUnit.SECONDS));
            // 立即获取结果，否则返回默认值
            System.out.println(future.getNow("noResult"));
        } catch (Exception e) {
           e.printStackTrace();
        }*/

        // 4.给future填充一个正常完成的结果
        future.complete("success result");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 5.给future填充一个异常结果
        future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("error result message"));
        try {
            future.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //导致这个 throwable 被抛出的 throwable
            System.out.println(e.getCause().getMessage());
        }
    }

    /**
     * 二、创建CompleteFuture方式
     */
    private static void testCreateCompleteFuture() {
        // 上面的示例（testBasicCompleteFutureUsage()方法使用样例中）是自己设置future的result，
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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
        });
    }

    /**
     * CompletableFuture的完成动作
     *
     *     public CompletableFuture<T>     whenComplete(BiConsumer<? super T,? super Throwable> action)
     *     public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     *     public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     *     public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)
     */
    public static void testCompleteAction() {

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
    }

    /**
     * CompletableFuture：多个 CompletableFuture 组合，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序
     */
    public static void testMultiplyCompleteStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFutureA = CompletableFuture.completedFuture("A");
        CompletableFuture<Integer> completableFutureB = CompletableFuture.completedFuture(123);

        // 1、thenAccept 无返回值
        /*final CompletableFuture<Void> thenAcceptCompletableFuture = completableFutureA.thenAccept(item -> {
            System.out.println(item);
        });
        completableFutureA.thenAcceptAsync(item -> {
            System.out.println(item);
        });
        completableFutureA.thenAcceptBoth(completableFutureB, (a, b) -> {
            System.out.println(a); // A
            System.out.println(b); // 123
        });*/


        ///2、thenRun 无返回值, 同 thenAccept
//        final CompletableFuture<Void> runnableCompletableFuture = completableFutureA.thenRun(() -> System.out.println("Runnable"));

        // thenApply 有返回值
        //thenApply（）转换的是泛型中的类型，是同一个CompletableFuture，相当于将CompletableFuture<T> 转换成CompletableFuture<U>
//        final CompletableFuture<String> thenApplyCompletableFuture = completableFutureA.thenApply(item -> item + item);


        //3、thenCompose: 两个或者多个 CompletableFuture 组合
         /*
         thenCompose的特点
        thenCompose 可以用于组合多个CompletableFuture，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
        thenCompose方法会在某个任务执行完成后，将该任务的执行结果作为方法入参然后执行指定的方法，该方法会返回一个新的CompletableFuture实例。
          */
        // thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
        /*final CompletableFuture<String> result = completableFutureA.thenCompose(a -> {
            System.out.println(a); // A
            CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(123);
            final CompletableFuture<String> newCompletableFutureB = completableFuture.thenApply(item -> item + "-" + a);
            return newCompletableFutureB;
        });
        System.out.println(result.get());// 123-A

        System.out.println("----------------------");
        final CompletableFuture<String> result2 = completableFutureA.thenCompose(a -> CompletableFuture.supplyAsync(() -> {
            System.out.println(a); // A
            return "supplyAsync-" + a;
        }));
        System.out.println(result2.get()); // supplyAsync-A*/


        // 4、thenCombine
        /*
        thenCombine会在两个任务都执行完成后，把两个任务的结果合并。
        注意：
            两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果。
            两个任务是并行执行的，它们之间并没有先后依赖顺序。
         */
        //第1个任务：获取人员姓名
        CompletableFuture<String> usernameFuture = CompletableFuture
                .supplyAsync(() -> {
//                    int a = 1 / 0;//出了异常就报错，并将异常当作结果返回
                    return "bkhech";
                });

        //第2个任务：获取人员年龄
        CompletableFuture<Integer> userAgeFuture = CompletableFuture
                .supplyAsync(() -> {
                    return 20;
                });

        //将上面2个任务的返回结果username和age合并，返回新的结果：UserInfo
        CompletableFuture<UserInfo> userInfoResultFuture = usernameFuture
                .thenCombine(userAgeFuture,
                        new BiFunction<String, Integer, UserInfo>() {
                            @Override
                            public UserInfo apply(String username, Integer age) {
                                return new UserInfo(username, age);
                            }
                        }
                );
        System.out.println("线程：" + Thread.currentThread().getName() + " 结果：" + userInfoResultFuture.get());

    }
    @AllArgsConstructor
    @Data
    private static class UserInfo {
        private String name;
        private Integer age;
    }

    /**
     * 阻塞获取结果，这种写法（ return completableFuture.join();）并不会阻塞
     */
    private static void testBlockGetResult() {
        CompletableFuture.supplyAsync(() -> {
            CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("123")
                    .thenApply(item -> {
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return item;
                    });
            System.out.println("---");
            return completableFuture.join();
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(s);
            }
        });
    }

    /**
     * acceptEither、applyToEither、runAfterEither 使用样例
     *
     * 以下主要举例说明 acceptEither 使用.
     * acceptEither方法返回一个新的CompletionStage，当此阶段或另一个给定阶段正常完成时，将使用相应的结果作为所提供操作的参数来执行该过程。
     * acceptEither方法是使用此阶段或其他给定阶段的结果执行的，无论哪个通常较早完成。
     * 从Java文档中找到acceptEither方法的方法声明。
     *
     * CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
     * 参数other是另一个CompletionStage。
     * 参数action是在完成返回的CompletionStage之前要执行的动作。
     */
    private static void testAcceptEither() throws InterruptedException {
        //acceptEither方法是使用此阶段或其他给定阶段的结果执行的，无论哪个通常较早完成。
        //因此，在我们的示例中，有时输出为“ Welcome ABC”，有时输出为“ Welcome XYZ”。
        IntStream.range(1, 50).forEach(item -> {
            CompletableFuture.supplyAsync(() -> "Welcome ABC")
                    .acceptEither(CompletableFuture.supplyAsync(() -> "Welcome XYZ"), s -> System.out.println(s));
        });

        //我们可以看到otherCFuture将比cfuture更早完成，因为getB()将比getA()方法更早完成。
        //因此，acceptEither方法将与otherCFuture完成阶段的结果一起执行。
        CompletableFuture<String> cfuture = CompletableFuture.supplyAsync(() -> getA());
        CompletableFuture<String> otherCFuture = CompletableFuture.supplyAsync(() -> getB());
        cfuture.acceptEither(otherCFuture, s -> System.out.println(s));

        new CountDownLatch(1).await();
    }
    private static String getA() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return "Mahesh";
    }
    private static String getB() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return "Krishna";
    }

    /**
     * 1.Consumer 使用样例
     * 2.Function 使用样例
     */
    private static void testFunctionConsumer() {
        // 1.Consumer 使用样例
        Consumer<String> consumer1 = t -> System.out.println("1" + t);
        Consumer<String> consumer2 = t -> System.out.println("2" + t);
        Consumer<String> consumer3 = t -> System.out.println("3" + t);
        consumer1.andThen(consumer2).andThen(consumer3).accept("===");

        // 2.Function 使用样例
        Function<Integer, String> fun1 = (x) -> x + "==fun1";
        Function<String, String> fun2After = (x) -> x + "==fun2";
        // 返回一个 先执行当前函数对象apply方法， 再执行after函数对象apply方法的 函数对象。
        // 1000作为fun1的参数，返回一个结果，该结果作为fun2After的参数，返回一个最终结果
        // 执行顺序：fun1Ret = fun1.apply("1000") -> fun2After.apply(fun1Ret)
        String applyRet = fun1.andThen(fun2After).apply(1000);
        System.out.println(applyRet);

        Function<Integer, String> fun3 = (x) -> x + "==fun1";
        Function<String, Integer> fun4Before = (x) -> Integer.parseInt(x);
        // 返回一个 先执行before函数对象apply方法，再执行当前函数对象apply方法的 函数对象。
        // "1000"作为fun4Before的参数，返回一个结果，该结果作为fun3的参数，返回一个最终结果
        // 执行顺序：fun4BeforeRet = fun4Before.apply("1000") -> fun3.apply(fun4BeforeRet)
        String composeRet = fun3.compose(fun4Before).apply("1000");
        System.out.println(composeRet);
    }

}
