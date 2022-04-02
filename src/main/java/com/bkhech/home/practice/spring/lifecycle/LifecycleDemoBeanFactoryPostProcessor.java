package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor
 *  Spring的BeanFactoryPostProcessor和BeanPostProcessor:
 * {@literal https://blog.csdn.net/caihaijiang/article/details/35552859}
 *
 * @author guowm
 * @date 2021/12/17
 */
public class LifecycleDemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public LifecycleDemoBeanFactoryPostProcessor() {
        System.out.println("LifecycleDemoBeanFactoryPostProcessor constructor");
    }

    /**
     * Modify the application context's internal bean factory after its standard
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            final BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
        }
        System.out.println("LifecycleDemoBeanFactoryPostProcessor postProcessBeanFactory");
    }
}
