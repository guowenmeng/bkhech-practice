package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleDemoConfig {

    @Bean(initMethod = "initMethod")
    public LifecycleDemo lifecycleDemo() {
        return new LifecycleDemo();
    }

    @Bean
    public LifecycleDemoBeanPostProcessor lifecycleDemoBeanPostProcessor() {
        return new LifecycleDemoBeanPostProcessor();
    }
}
