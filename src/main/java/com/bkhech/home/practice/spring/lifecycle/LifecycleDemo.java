package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * spring lifecycle demo
 * BeanNameAware > BeanClassLoaderAware > BeanFactoryAware > BeanPostProcessor.postProcessBeforeInitialization > @PostConstruct > InitializingBean > SmartInitializingSingleton > BeanPostProcessor.postProcessAfterInitialization > DisposableBean
 * (     ↑               各种 Aware               ↑         )
 * 1.LifecycleDemoBeanPostProcessor constructor
 * 2.LifecycleDemo constructor
 * 3.LifecycleDemo setBeanName---lifecycleDemo
 * 4.LifecycleDemo setBeanClassLoader--sun.misc.Launcher$AppClassLoader@18b4aac2
 * 5.LifecycleDemo setBeanFactory
 * 6.LifecycleDemo postProcessBeforeInitialization----lifecycleDemo
 * 7.LifecycleDemo @PostConstruct
 * 8.LifecycleDemo afterPropertiesSet
 * 9.LifecycleDemo initMethod
 * 10.LifecycleDemo postProcessAfterInitialization----lifecycleDemo
 * 11.LifecycleDemo afterSingletonsInstantiated
 *
 * 说明：
 * 其中第 1 和第 2 步为对象实例化过程，第 6 步为对象初始化前置处理器
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
