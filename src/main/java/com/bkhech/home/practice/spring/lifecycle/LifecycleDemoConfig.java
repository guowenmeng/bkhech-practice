package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class LifecycleDemoConfig {

    @Bean(initMethod = "initMethod")
    public LifecycleDemo2 lifecycleDemo2() {
        return new LifecycleDemo2();
    }

    @Bean(initMethod = "initMethod")
    public LifecycleDemo lifecycleDemo() {
        return new LifecycleDemo();
    }

    @Bean
    public LifecycleDemoBeanPostProcessor lifecycleDemoBeanPostProcessor() {
        return new LifecycleDemoBeanPostProcessor();
    }

    @Bean
    public LifecycleDemoBeanFactoryPostProcessor2 lifecycleDemoBeanFactoryPostProcessor2() {
        return new LifecycleDemoBeanFactoryPostProcessor2();
    }

    @Bean
    public LifecycleDemoBeanPostProcessor2 lifecycleDemoBeanPostProcessor2() {
        return new LifecycleDemoBeanPostProcessor2();
    }

    @Bean
    public LifecycleDemoBeanFactoryPostProcessor lifecycleDemoBeanFactoryPostProcessor() {
        return new LifecycleDemoBeanFactoryPostProcessor();
    }

}
