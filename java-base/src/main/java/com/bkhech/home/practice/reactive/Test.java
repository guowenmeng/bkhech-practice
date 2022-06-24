package com.bkhech.home.practice.reactive;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test {

    static final Logger log = LoggerFactory.getLogger(Test.class);

    static final int PS = 2;

    static final BlockingQueue<String> waitBuffer = new ArrayBlockingQueue<>(128);

    public static void main(String[] args) throws InterruptedException {
        IntStream.range(1,10).boxed().forEach(item -> {
            waitBuffer.add(item.toString());
        });

        handle();

        TimeUnit.HOURS.sleep(1);
    }

    private static void handle() {
        Flowable.interval(5, TimeUnit.SECONDS).subscribe(item -> {
            waitBuffer.add("8888");
            waitBuffer.add("9999");
        });


        Flowable.fromIterable(() -> new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public String next() {
                while (true) {
                    try {
                        System.out.println("Thread.currentThread() ： " + Thread.currentThread().getId());
                        return waitBuffer.take();
                    } catch (Exception e) {
                        //ignore
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .buffer(1, TimeUnit.SECONDS, 5)
                .map(task -> {
                    System.out.println("task" + task);
                    TimeUnit.SECONDS.sleep(2);
                    return task;
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<List<String>>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        this.subscription.request(PS);
                    }

                    @Override
                    public void onNext(List<String> s) {
                        log.info("Completed task: {}", s);
                        if (!s.isEmpty()) {
                            System.out.println("handle" + s);
                        }
                        subscription.request(PS);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log.error("Test failure", t);
                    }

                    @Override
                    public void onComplete() {
                        log.info("Test completed");
                    }
                });
    }
}
