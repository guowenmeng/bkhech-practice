package com.bkhech.home.practice.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;

/**
 * spring lifecycle demo
 * @author guowm
 * @date 2021/5/14
 */
@Slf4j
//@Component
public class LifecycleDemo implements InitializingBean, SmartInitializingSingleton, BeanClassLoaderAware, BeanPostProcessor, BeanFactoryAware, BeanNameAware, DisposableBean {

   @PostConstruct
   public void init() {
       log.info("----------init---------------");
   }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("--------setBeanFactory------------{}");
    }

    @Override
    public void setBeanName(String name) {
        log.info("--------setBeanName------------{}", name);

    }

    @Override
    public void destroy() throws Exception {
        log.info("--------destroy------------{}");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("--------afterPropertiesSet------------{}");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("--------afterSingletonsInstantiated------------{}");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("--------setBeanClassLoader------------{}", classLoader);
    }

    /** ------------------------BeanPostProcessor 后置处理器----------------------------- */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if (bean.getClass().isAssignableFrom(LifecycleDemo.class)) {
           log.info("--------postProcessBeforeInitialization-------@Bean的init()方法之前执行-----{}", beanName);
       }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(LifecycleDemo.class)) {
            log.info("--------postProcessAfterInitialization--------@Bean的init()方法之后执行----{}", beanName);
        }
        return null;
    }
    /** ------------------------BeanPostProcessor 后置处理器----------------------------- */

}
