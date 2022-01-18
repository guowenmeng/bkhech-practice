package com.bkhech.home.practice.designpattern.chain_of_responsibility2.core;

/**
 * 拦截器抽象接口
 *
 * @author guowm
 * @date 2022/1/18
 */
public interface Filter<T> {
    /**
     * @param context
     * @param nextFilter
     */
    void doFilter(T context, FilterInvoker<T> nextFilter);
}
