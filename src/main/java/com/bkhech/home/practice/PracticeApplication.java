package com.bkhech.home.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
@RequestMapping
public class PracticeApplication {

    private AtomicInteger count = new AtomicInteger(0);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String hello(@RequestParam(required=false) String param) throws InterruptedException {
        while (true) {
            System.out.println(String.format("param>>>>>>>>>>>>>>:%s", param));
            System.out.println("add ...  = ");
            Thread.sleep(1000);
            count.incrementAndGet();
            if (count.get()==3) {
                System.out.println("==end==");
                break;
            }
        }
        return "hi, new company =>>>>>>>>>>>>";
    }


    public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
}
