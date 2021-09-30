package com.bkhech.home.practice.javacore.java8.completablefuture;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

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
        CompletableFuture<String> future = CompletableFuture.completedFuture("result");
        //join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出，
//        future.join();
       /* String str = future.get();
        System.out.println("str = " + str);*/

        // 默认创建的CompletableFuture是没有result的，
        // 这时调用future.get()会一直阻塞下去直到有result或者出现异常
        future = new CompletableFuture<>();
       /* try {
            future.get(1, TimeUnit.SECONDS);
//            System.out.println(future.getNow("noResult"));
        } catch (Exception e) {
//           e.printStackTrace();
            System.out.println("e.toString() = " + e.toString());
        }
*/
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
        /*CompletableFuture.supplyAsync(() -> {
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
        });*/

        //四、CompletableFuture：多个 CompletableFuture 组合，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序
        CompletableFuture<String> completableFutureA = CompletableFuture.completedFuture("A");
        CompletableFuture<Integer> completableFutureB = CompletableFuture.completedFuture(123);

        // 1、thenAccept 无返回值
        /*
        final CompletableFuture<Void> thenAcceptCompletableFuture = completableFutureA.thenAccept(item -> {
            System.out.println(item);
        });
        completableFutureA.thenAcceptAsync(item -> {
            System.out.println(item);
        });
        completableFutureA.thenAcceptBoth(completableFutureB, (a, b) -> {
            System.out.println(a); // A
            System.out.println(b); // B
        });*/

        ///2、thenRun 无返回值, 同 thenAccept
//        final CompletableFuture<Void> runnableCompletableFuture = completableFutureA.thenRun(() -> System.out.println("Runnable"));

        // thenApply 有返回值
        //thenApply（）转换的是泛型中的类型，是同一个CompletableFuture，相当于将CompletableFuture<T> 转换成CompletableFuture<U>
//        final CompletableFuture<String> thenApplyCompletableFuture =
//                completableFutureA.thenApply(item -> item + item);

        //3、两个或者多个 CompletableFuture 组合
         /*
         thenCompose的特点
        thenCompose 可以用于组合多个CompletableFuture，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
        thenCompose方法会在某个任务执行完成后，将该任务的执行结果作为方法入参然后执行指定的方法，该方法会返回一个新的CompletableFuture实例。
          */

        // thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
        /*final CompletableFuture<String> result = completableFutureA.thenCompose(a -> {
            System.out.println(a); // A
            CompletableFuture<Integer> completableFuture = CompletableFuture.completedFuture(123);
            final CompletableFuture<String> newCompletableFutureB = completableFuture.thenApply(item -> item + a);
            return newCompletableFutureB;
        });

        final CompletableFuture<String> result2 = completableFutureA.thenCompose(a -> CompletableFuture.supplyAsync(() -> {
            System.out.println(a); // A
            return "supplyAsync" + a;
        }));*/

        // 4、thenCombine
        /*
        thenCombine会在两个任务都执行完成后，把两个任务的结果合并。
        注意：
            两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果。
            两个任务是并行执行的，它们之间并没有先后依赖顺序。
         */
        //第1个任务: 获取部门
        /*CompletableFuture<String> deptFuture = CompletableFuture
                .supplyAsync(() -> {
                            return "dept";
                        }
                );

        //第2个任务：获取人员
        CompletableFuture<String> userFuture = CompletableFuture
                .supplyAsync(() -> {
                    int a = 1 / 0;//出了异常就报错
                    return "user";
                });

        //将上面2个任务的返回结果dept和user合并，返回新的结果：dept:user
        CompletableFuture<String> deptUserResultFuture = deptFuture
                .thenCombine(userFuture,
                        new BiFunction<String, String, String>() {
                            @Override
                            public String apply(String dept, String user) {
                                return dept + ":" + user;
                            }
                        }
                );
        System.out.println("线程：" + Thread.currentThread().getName() + " 结果：" + deptUserResultFuture.get());*/

        //五、阻塞获取结果，这种写法（ return completableFuture.join();）并不会阻塞
        /*CompletableFuture.supplyAsync(() -> {
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
        });*/

        // TODO 待测试
//        completableFutureA.acceptEither()


        Consumer<String> consumer = t -> {
            System.out.println("1" + t);
        };
        consumer.andThen(t -> {
            System.out.println("2" + t);
        }).andThen(t -> {
            System.out.println("3" + t);
        });

        consumer.accept("===");

        System.out.println("test end =线程：" + Thread.currentThread().getName());

        new CountDownLatch(1).await();
    }

}
