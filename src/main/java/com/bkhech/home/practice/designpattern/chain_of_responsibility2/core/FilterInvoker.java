package com.bkhech.home.practice.designpattern.chain_of_responsibility2.core;

/**
 * 拦截器链执行器
 *
 * @author guowm
 * @date 2022/1/18
 */
public interface FilterInvoker<T> {
    /**
     * 执行
     * @param context
     */
    default void invoke(T context) {
    }
}
