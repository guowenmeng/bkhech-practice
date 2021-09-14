package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * spring lifecycle demo
 * BeanNameAware > BeanClassLoaderAware > BeanFactoryAware > BeanPostProcessor.postProcessBeforeInitialization > @PostConstruct > InitializingBean > SmartInitializingSingleton > BeanPostProcessor.postProcessAfterInitialization > DisposableBean
 *                        各种 Aware                        >
 * LifecycleDemo constructor
 * LifecycleDemo setBeanName---lifecycleDemo
 * LifecycleDemo setBeanClassLoader--sun.misc.Launcher$AppClassLoader@18b4aac2
 * LifecycleDemo setBeanFactory
 * LifecycleDemo postProcessBeforeInitialization----lifecycleDemo
 * LifecycleDemo @PostConstruct
 * LifecycleDemo afterPropertiesSet
 * LifecycleDemo initMethod
 * LifecycleDemo postProcessAfterInitialization----lifecycleDemo
 * LifecycleDemo afterSingletonsInstantiated
 *
 * @author guowm
 * @date 2021/5/14
 */
public class LifecycleDemo implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        InitializingBean,
        SmartInitializingSingleton,
        DisposableBean {

    public LifecycleDemo() {
        System.out.println("LifecycleDemo constructor");
    }

    @PostConstruct
   public void initPostConstruct() {
       System.out.println("LifecycleDemo @PostConstruct");
   }

    public void initMethod() {
        System.out.println("LifecycleDemo initMethod");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("LifecycleDemo setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("LifecycleDemo setBeanName---" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("LifecycleDemo destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifecycleDemo afterPropertiesSet");
    }

    @Override
    public void afterSingletonsInstantiated() {
       //实例化之后执行
        System.out.println("LifecycleDemo afterSingletonsInstantiated");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("LifecycleDemo setBeanClassLoader--" + classLoader);
    }

}
