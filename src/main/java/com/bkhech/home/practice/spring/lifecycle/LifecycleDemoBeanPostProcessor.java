package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * LifecycleDemoBeanPostProcessor
 * @author guowm
 * @date 2021/5/14
 */
public class LifecycleDemoBeanPostProcessor implements BeanPostProcessor {

    public LifecycleDemoBeanPostProcessor() {
        System.out.println("LifecycleDemoBeanPostProcessor constructor");
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifecycleDemo) {
            System.out.println("LifecycleDemo postProcessBeforeInitialization----" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifecycleDemo) {
            System.out.println("LifecycleDemo postProcessAfterInitialization----" + beanName);
        }
        return bean;
    }

}
