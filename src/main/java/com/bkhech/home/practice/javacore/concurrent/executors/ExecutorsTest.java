package com.bkhech.home.practice.javacore.concurrent.executors;

import java.util.concurrent.*;

/**
 * @author guowm
 * @date 2019/9/25
 * @description
 */
public class ExecutorsTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TaskRunnale runnale = new TaskRunnale("runnale");
        TaskCallable callable = new TaskCallable("callable");

        executorService.execute(runnale);

        Future<?> submit = executorService.submit(runnale);
        //完成后返回null
        System.out.println("submit.get() = " + submit.get());

        User user = new User("user");

        Future<User> abc = executorService.submit(runnale, user);
        //完成后返回user值
        System.out.println("abc.get() = " + abc.get());

        Future<String> submit1 = executorService.submit(callable);
        //完成后返回TaskRunnaler任务返回的结果
        System.out.println("submit1.get() = " + submit1.get());

    }


    static class TaskRunnale implements Runnable {

        private String name;

        public TaskRunnale(String name) {
            this.name = name;
        }

        @Override
        public void run() {
        }
    }

    static class TaskCallable implements Callable<String> {

        private String name;

        public TaskCallable(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            return name;
        }
    }

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
