package com.bkhech.home.practice.spring.springioc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author guowm
 * @date 2021/3/3
 */
//@Configuration(proxyBeanMethods = false) // Full模式，默认
@Configuration(proxyBeanMethods = false) // Lite模式
public class LiteConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setName("A哥-lite");
        user.setAge(18);
        return user;
    }

    @Bean
    public User user2() {
//        private final User user2() {  // 只在lite模式下才好使
            User user = new User();
            user.setName("A哥-lite2");
            user.setAge(18);

            // 模拟依赖于user实例  看看是否是同一实例
//        System.out.println(System.identityHashCode(user()));
//        System.out.println(System.identityHashCode(user()));

            System.out.println(user());
            System.out.println(user());

            return user;
        }

        public static class InnerConfig {

            @Bean
            // private final User userInner() { // 只在lite模式下才好使
            public User userInner() {
                User user = new User();
                user.setName("A哥-lite-inner");
                user.setAge(18);
                return user;
            }
        }
    }


