package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * LifecycleDemo2
 * @author guowm
 * @date 2021/12/17
 */
public class LifecycleDemo2 implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        EnvironmentAware,
        InitializingBean,
        SmartInitializingSingleton,
        DisposableBean {

    private String name = "dddd";

    public LifecycleDemo2() {
        this.name = "bkhech";
        System.out.println("LifecycleDemo2 constructor");
    }

    @PostConstruct
   public void initPostConstruct() {
       System.out.println("LifecycleDemo2 @PostConstruct");
   }

    public void initMethod() {
        System.out.println("LifecycleDemo2 initMethod");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("LifecycleDemo2 setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("LifecycleDemo2 setBeanName---" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("LifecycleDemo2 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("LifecycleDemo2 afterPropertiesSet");
    }

    @Override
    public void afterSingletonsInstantiated() {
       //实例化之后执行
        System.out.println("LifecycleDemo2 afterSingletonsInstantiated");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("LifecycleDemo2 setBeanClassLoader");
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("LifecycleDemo2 environment");
    }

}
