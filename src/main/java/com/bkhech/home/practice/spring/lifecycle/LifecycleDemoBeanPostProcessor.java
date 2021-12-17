package com.bkhech.home.practice.spring.lifecycle;

import com.bkhech.home.practice.spring.springioc.configuration.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * LifecycleDemoBeanPostProcessor
 * BeanPostProcessor：所有 bean 的后置处理器
 * 所有的 bean 都已经实例化完成（see {@link LifecycleDemo}），
 * 然后所有 bean 都会回调 BeanPostProcessor 里的两个方法，所以要根据条件处理业务逻辑
 *
 * @author guowm
 * @date 2021/5/14
 */
public class LifecycleDemoBeanPostProcessor implements BeanPostProcessor {

    public LifecycleDemoBeanPostProcessor() {
        System.out.println("LifecycleDemoBeanPostProcessor constructor");
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 根据条件处理业务逻辑
        if (bean instanceof LifecycleDemo || bean instanceof LifecycleDemo2 || bean instanceof User) {
            System.out.println("LifecycleDemoBeanPostProcessor postProcessBeforeInitialization----" + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifecycleDemo || bean instanceof LifecycleDemo2 || bean instanceof User) {
            System.out.println("LifecycleDemoBeanPostProcessor postProcessAfterInitialization----" + beanName);
        }
        return bean;
    }

}
