package com.bkhech.home.practice.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * 关于 jvm 层面类生命周期：
 * {@literal https://blog.csdn.net/qq_43515464/article/details/109018821}
 * {@literal https://www.cnblogs.com/pu20065226/p/12206463.html#:~:text=%E5%88%9D%E5%A7%8B%E5%8C%96%E5%8F%AA%E5%9C%A8%E7%B1%BB%E5%8A%A0%E8%BD%BD%E7%9A%84%E6%97%B6%E5%80%99%E6%89%A7%E8%A1%8C%E4%B8%80%E6%AC%A1%E3%80%82%20%E7%B1%BB%E7%9A%84%E5%AE%9E%E4%BE%8B%E5%8C%96%EF%BC%9A%E6%98%AF%E6%8C%87%E5%88%9B%E5%BB%BA%E4%B8%80%E4%B8%AA%E5%AF%B9%E8%B1%A1%E7%9A%84%E8%BF%87%E7%A8%8B%E3%80%82%20%E8%BF%99%E4%B8%AA%E8%BF%87%E7%A8%8B%E4%B8%AD%E4%BC%9A%E5%9C%A8%E5%A0%86%E4%B8%AD%E5%BC%80%E8%BE%9F%E5%86%85%E5%AD%98%EF%BC%8C%E5%B0%86%E4%B8%80%E4%BA%9B%E9%9D%9E%E9%9D%99%E6%80%81%E7%9A%84%E6%96%B9%E6%B3%95%EF%BC%8C%E5%8F%98%E9%87%8F%E5%AD%98%E6%94%BE%E5%9C%A8%E9%87%8C%E9%9D%A2%E3%80%82%20%E5%9C%A8%E7%A8%8B%E5%BA%8F%E6%89%A7%E8%A1%8C%E7%9A%84%E8%BF%87%E7%A8%8B%E4%B8%AD%EF%BC%8C%E5%8F%AF%E4%BB%A5%E5%88%9B%E5%BB%BA%E5%A4%9A%E4%B8%AA%E5%AF%B9%E8%B1%A1%EF%BC%8C%E6%97%A2%E5%A4%9A%E6%AC%A1%E5%AE%9E%E4%BE%8B%E5%8C%96%E3%80%82%20%E6%AF%8F%E6%AC%A1%E5%AE%9E%E4%BE%8B%E5%8C%96%E9%83%BD%E4%BC%9A%E5%BC%80%E8%BE%9F%E4%B8%80%E5%9D%97%E6%96%B0%E7%9A%84%E5%86%85%E5%AD%98%E3%80%82,%E6%8C%87%E4%B8%80%E4%B8%AAclass%E6%96%87%E4%BB%B6%E4%BB%8E%E5%8A%A0%E8%BD%BD%E5%88%B0%E5%8D%B8%E8%BD%BD%E7%9A%84%E5%85%A8%E8%BF%87%E7%A8%8B%EF%BC%8C%E7%B1%BB%E7%9A%84%E5%AE%8C%E6%95%B4%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E5%8C%85%E6%8B%AC7%E4%B8%AA%E9%83%A8%E5%88%86%EF%BC%9A%E5%8A%A0%E8%BD%BD%E2%80%94%E2%80%94%E9%AA%8C%E8%AF%81%E2%80%94%E2%80%94%E5%87%86%E5%A4%87%E2%80%94%E2%80%94%E8%A7%A3%E6%9E%90%E2%80%94%E2%80%94%E5%88%9D%E5%A7%8B%E5%8C%96%E2%80%94%E2%80%94%E4%BD%BF%E7%94%A8%E2%80%94%E2%80%94%E5%8D%B8%E8%BD%BD%EF%BC%8C%E5%A6%82%E4%B8%8B%E5%9B%BE%E6%89%80%E7%A4%BA%20%E5%85%B6%E4%B8%AD%EF%BC%8C%E9%AA%8C%E8%AF%81%E2%80%94%E2%80%94%E5%87%86%E5%A4%87%E2%80%94%E2%80%94%E8%A7%A3%E6%9E%90%20%E7%A7%B0%E4%B8%BA%E8%BF%9E%E6%8E%A5%E9%98%B6%E6%AE%B5%EF%BC%8C%E9%99%A4%E4%BA%86%E8%A7%A3%E6%9E%90%E5%A4%96%EF%BC%8C%E5%85%B6%E4%BB%96%E9%98%B6%E6%AE%B5%E6%98%AF%E9%A1%BA%E5%BA%8F%E5%8F%91%E7%94%9F%E7%9A%84%EF%BC%8C%E8%80%8C%E8%A7%A3%E6%9E%90%E5%8F%AF%E4%BB%A5%E4%B8%8E%E8%BF%99%E4%BA%9B%E9%98%B6%E6%AE%B5%E4%BA%A4%E5%8F%89%E8%BF%9B%E8%A1%8C%EF%BC%8C%E5%9B%A0%E4%B8%BAJava%E6%94%AF%E6%8C%81%E5%8A%A8%E6%80%81%E7%BB%91%E5%AE%9A%EF%BC%88%E6%99%9A%E6%9C%9F%E7%BB%91%E5%AE%9A%EF%BC%89%EF%BC%8C%E9%9C%80%E8%A6%81%E8%BF%90%E8%A1%8C%E6%97%B6%E6%89%8D%E8%83%BD%E7%A1%AE%E5%AE%9A%E5%85%B7%E4%BD%93%E7%B1%BB%E5%9E%8B%EF%BC%9B%E5%9C%A8%E4%BD%BF%E7%94%A8%E9%98%B6%E6%AE%B5%E5%AE%9E%E4%BE%8B%E5%8C%96%E5%AF%B9%E8%B1%A1%20%E5%8A%A0%E8%BD%BD%EF%BC%9A%E9%80%9A%E8%BF%87%E7%B1%BB%E5%90%8D%E8%8E%B7%E5%8F%96%E7%B1%BB%E7%9A%84%E4%BA%8C%E8%BF%9B%E5%88%B6%E5%AD%97%E8%8A%82%E6%B5%81%E6%98%AF%E9%80%9A%E8%BF%87%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8%E6%9D%A5%E5%AE%8C%E6%88%90%E7%9A%84%E3%80%82%20%E5%85%B6%E5%8A%A0%E8%BD%BD%E8%BF%87%E7%A8%8B%E4%BD%BF%E7%94%A8%E2%80%9C%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%A8%A1%E5%9E%8B%E2%80%9D}
 *
 *
 * spring lifecycle demo
 *BeanFactoryPostProcessor              BeanNameAware > BeanClassLoaderAware > BeanFactoryAware > BeanPostProcessor.postProcessBeforeInitialization > @PostConstruct > InitializingBean > SmartInitializingSingleton > BeanPostProcessor.postProcessAfterInitialization > DisposableBean
 *              (     ↑               各种 Aware               ↑         )
 *
 * --------- BeanFactoryPostProcessor 实例化 ---------------------------- 在所有 bean 实例化之前执行 -----------
 * LifecycleDemoBeanFactoryPostProcessor2 constructor
 * LifecycleDemoBeanFactoryPostProcessor constructor
 * --------- BeanFactoryPostProcessor postProcessBeanFactory 回调 ------ 在所有 bean 实例化之前执行 ----------
 * LifecycleDemoBeanFactoryPostProcessor2 postProcessBeanFactory
 * LifecycleDemoBeanFactoryPostProcessor postProcessBeanFactory
 *
 * --------- BeanPostProcessor 实例化 ----------------------------------- 在所有 bean 实例化之前执行 ------------
 * LifecycleDemoBeanPostProcessor constructor
 * LifecycleDemoBeanPostProcessor2 constructor
 *
 * ------------------------------ 所有单例 bean 实例化, 回调初始化方法 开始 -------------------------------------------
 * ####################### 单例 bean1 实例化开始 #######################
 * LifecycleDemo2 constructor
 * LifecycleDemo2 setBeanName---lifecycleDemo2
 * LifecycleDemo2 setBeanClassLoader
 * LifecycleDemo2 setBeanFactory
 * LifecycleDemo2 environment
 * ~~~~~~~~~~~~~~~~~~~~~~ LifecycleDemo2 回调 BeanPostProcessor 中的方法 ~~~~~~~ start ~~~~~~~
 * LifecycleDemoBeanPostProcessor postProcessBeforeInitialization----lifecycleDemo2
 * LifecycleDemoBeanPostProcessor2 postProcessBeforeInitialization----lifecycleDemo2
 * ~~~~~~~~~~~~~~~~~~~~~~ LifecycleDemo2 回调 BeanPostProcessor 中的方法 ~~~~~~~ end ~~~~~~~
 * LifecycleDemo2 @PostConstruct
 * LifecycleDemo2 afterPropertiesSet
 * LifecycleDemo2 initMethod
 * LifecycleDemoBeanPostProcessor postProcessAfterInitialization----lifecycleDemo2
 * LifecycleDemoBeanPostProcessor2 postProcessAfterInitialization----lifecycleDemo2
 *
 * ####################### 单例 bean2 实例化开始 #######################
 * LifecycleDemo constructor
 * LifecycleDemo setBeanName---lifecycleDemo
 * LifecycleDemo setBeanClassLoader
 * LifecycleDemo setBeanFactory
 * LifecycleDemo environment
 * ~~~~~~~~~~~~~~~~~~~~~~ LifecycleDemo 回调 BeanPostProcessor 中的方法 ~~~~~~~ start ~~~~~~~
 * LifecycleDemoBeanPostProcessor postProcessBeforeInitialization----lifecycleDemo
 * LifecycleDemoBeanPostProcessor2 postProcessBeforeInitialization----lifecycleDemo
 * ~~~~~~~~~~~~~~~~~~~~~~ LifecycleDemo 回调 BeanPostProcessor 中的方法 ~~~~~~~ end ~~~~~~~
 * LifecycleDemo @PostConstruct
 * LifecycleDemo afterPropertiesSet
 * LifecycleDemo initMethod
 * LifecycleDemoBeanPostProcessor postProcessAfterInitialization----lifecycleDemo
 * LifecycleDemoBeanPostProcessor2 postProcessAfterInitialization----lifecycleDemo
 * ------------------------------ 所有单例 bean 实例化, 回调初始化方法 结束 -------------------------------------------

 * ------------------------------ 在所有单例 Bean 都初始化完成后调用 ------------------------------------
 * LifecycleDemo2 afterSingletonsInstantiated
 * LifecycleDemo afterSingletonsInstantiated
 *
 * 说明：
 * 解释：在 jvm 层面，初始化(类和静态的只在内存中加载一次)：类初始化， 实例化（普通属性或者普通代码块，然后执行构造方法）: 实例对象
 *
 *
 * 拿这个来说：
 *
 *  *  1. LifecycleDemo2 constructor
 *  *  2. LifecycleDemo2 setBeanName---lifecycleDemo2
 *  *  3. LifecycleDemo2 setBeanClassLoader
 *  *  4. LifecycleDemo2 setBeanFactory
 *  *  5. LifecycleDemo2 environment
 *  *  6. LifecycleDemoBeanPostProcessor postProcessBeforeInitialization----lifecycleDemo2
 *  *  7. LifecycleDemoBeanPostProcessor2 postProcessBeforeInitialization----lifecycleDemo2
 *  *  8. LifecycleDemo2 @PostConstruct
 *  *  9. LifecycleDemo2 afterPropertiesSet
 *  * 10. LifecycleDemo2 initMethod
 *  * 11. LifecycleDemoBeanPostProcessor postProcessAfterInitialization----lifecycleDemo2
 *  * 12. LifecycleDemoBeanPostProcessor2 postProcessAfterInitialization----lifecycleDemo2
 *
 * 其中：
 * 第 1 步为实例化过程（jvm 层面的实例化概念），
 * 第 6 和 7 步为容器对象初始化方法回调前置处理器（解释：此时表示的是 spring 层面对象的初始化方法，
 *          特指：Initialization，InitMethod, @PostConstruct这些初始化方法，容易和 jvm 层面的对象初始化搞混）,
 * 第 8、9、10 步是容器对象初始化方法（spring 层面）回调
 * 第 11 和 12 步为容器对象初始化方法回调后置处理器
 *
 * @author guowm
 * @date 2021/5/14
 */
public class LifecycleDemo implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        EnvironmentAware,
        InitializingBean,
        SmartInitializingSingleton,
        DisposableBean {

    private String name = "dddd";

    public LifecycleDemo() {
        this.name = "bkhech";
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
        System.out.println("LifecycleDemo setBeanClassLoader");
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("LifecycleDemo environment");
    }

}
