package com.bkhech.home.practice.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * spring lifecycle demo
 * @author guowm
 * @date 2021/5/14
 */
@Slf4j
@Component
public class LifecycleDemo2 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--------postProcessBeforeInitialization------------" + beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--------postProcessAfterInitialization------------" + beanName);
        return null;
    }

}
