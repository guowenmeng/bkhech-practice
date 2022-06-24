package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor2
 *
 * @author guowm
 * @date 2021/12/17
 */
public class LifecycleDemoBeanFactoryPostProcessor2 implements BeanFactoryPostProcessor {

    public LifecycleDemoBeanFactoryPostProcessor2() {
        System.out.println("LifecycleDemoBeanFactoryPostProcessor2 constructor");
    }

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("LifecycleDemoBeanFactoryPostProcessor2 postProcessBeanFactory");
    }
}
