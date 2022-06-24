package com.bkhech.home.practice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@SpringBootApplication
@RequestMapping
public class PracticeApplication {

    private final AtomicInteger count = new AtomicInteger(0);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hello(@RequestParam(required=false) String param) throws InterruptedException {
        while (true) {
            System.out.println(String.format("param>>>>>>>>>>>>>>:%s", param));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.incrementAndGet();
            if (count.get()==3) {
                System.out.println("==end==");
                count.set(0);
                break;
            }
        }
        return "hi, new company =>>>>>>>>>>>>" + param;
    }

    @GetMapping(value = "/hello/reactor", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<String> helloReactor(@RequestParam(required=false) String param) {
        return Mono.defer(() -> {
            while (true) {
                System.out.println(String.format("param>>>>>>>>reactor>>>>>>:%s", param));
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.incrementAndGet();
                if (count.get()==3) {
                    System.out.println("=reactor=end==");
                    count.set(0);
                    break;
                }
            }
            return Mono.just("hi, new company =>>>>>>>reactor>>>>>" + param);
        });
    }


    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
	}
}
