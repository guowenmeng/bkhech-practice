package com.bkhech.home.practice.distributed.限流降级;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guowm
 * @date 2019/10/10
 * @description
 */
public class Test {
    final static String url = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLj7r4eb4gD8192fFNZNhSicw7FddvBVgaJD5YYURorawPRP57EiaSIs98icaiaWv7Upf79haWLe9skeA/132";
    static RestTemplate restTemplate = new RestTemplate();
    static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {

        try {
            for (int i = 0; i < 20; i++) {
                executorService.execute(new Task());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            restTemplate.getForObject(url, String.class);
        }
    }

}
