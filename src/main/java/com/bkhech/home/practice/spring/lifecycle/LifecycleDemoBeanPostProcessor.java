package com.bkhech.home.practice.spring.lifecycle;

import com.bkhech.home.practice.spring.springioc.configuration.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
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
public class LifecycleDemoBeanPostProcessor implements BeanPostProcessor, InstantiationAwareBeanPostProcessor {

    public LifecycleDemoBeanPostProcessor() {
        System.out.println("LifecycleDemoBeanPostProcessor constructor");
    }

    /** ------------------BeanPostProcessor 中方法 start---------------------------------------*/
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
    /** ------------------BeanPostProcessor 中方法 end---------------------------------------*/


    /** ------------------InstantiationAwareBeanPostProcessor 中方法 start---------------------------------------*/
    @Override
    @Nullable
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.isAssignableFrom(LifecycleDemo.class) || beanClass.isAssignableFrom(LifecycleDemo2.class) || beanClass.isAssignableFrom(User.class)) {
            System.out.println("LifecycleDemoBeanPostProcessor postProcessBeforeInstantiation----" + beanName);
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof LifecycleDemo || bean instanceof LifecycleDemo2 || bean instanceof User) {
            System.out.println("LifecycleDemoBeanPostProcessor postProcessAfterInstantiation----" + beanName);
        }
        return true;
    }

    @Override
    @Nullable
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
            throws BeansException {
        if (bean instanceof LifecycleDemo || bean instanceof LifecycleDemo2 || bean instanceof User) {
            System.out.println("LifecycleDemoBeanPostProcessor postProcessProperties----" + beanName);
        }
        return null;
    }
    /** ------------------InstantiationAwareBeanPostProcessor 中方法 end---------------------------------------*/


}
