package com.bkhech.home.practice.javacore.concurrent.executors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * ExecutorService Shutdown() ShutdownNow()
 * @author guowm
 * @date 2021/9/10
 */
@Slf4j
@Component
public class ShutdownAndShutdownNowDemo implements InitializingBean, DisposableBean {

    final static ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            //默认 deamon=false
            thread.setDaemon(true);
            return thread;
        }
    });

    public static void initTask() {
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName()+" start....");
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("打断。。。。。");
            }
            System.out.println(Thread.currentThread().getName()+" end....");
        };

        for (int i = 0; i < 10; i++) {
            executorService.submit(runnable);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO 可打开注释进行测试
//        initTask();
    }


    @Override
    public void destroy() throws Exception {
        log.info("-------------------start");
        executorService.shutdown();
        //等待一定时间 executorService 中已提交的所有任务执行（包括正在执行和在队列中未执行的任务）
        // 若队列中积压大量任务，则在指定的等待时间内未完成的任务会丢失

        // 队列的维护：提供了 getQueue () 方法方便我们进行监控和调试，严禁用于其他目的.
        // 即：使用ThreadPoolExecutor可以获取队列中的任务，可以做后台监控系统，监控线程池中的任务

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        log.info("-------------------end");
    }

}
