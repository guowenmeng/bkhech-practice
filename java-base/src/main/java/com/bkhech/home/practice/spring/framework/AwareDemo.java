package com.bkhech.home.practice.spring.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @author guowm
 * @date 2021/1/18
 * @description 接口回调注入
 */
public class AwareDemo implements
        ApplicationContextAware, //获取Spring应用上下文
        BeanFactoryAware, //获取当前IOC容器
        EmbeddedValueResolverAware, // 获取StringValueResolver对象，用于占位符处理
        EnvironmentAware, // 获取Environment对象
        ResourceLoaderAware, // 获取资源加载器对象
        ApplicationEventPublisherAware, //获取ApplicationEventPublisher对象，用于Spring事件
        BeanClassLoaderAware, //获取加载当前Bean Class 的ClassLoader
        BeanNameAware, //获取当前Bean名称
        MessageSourceAware // 获取MessageResource对象，用于Spring国际化
{

    ApplicationContext applicationContext;
    BeanFactory beanFactory;
    StringValueResolver resolver;
    Environment environment;
    ResourceLoader resourceLoader;
    ClassLoader classLoader;
    MessageSource messageSource;
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean名称为：" + name);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
