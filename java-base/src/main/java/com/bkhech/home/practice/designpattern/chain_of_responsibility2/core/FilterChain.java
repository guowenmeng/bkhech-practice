package com.bkhech.home.practice.designpattern.chain_of_responsibility2.core;

/**
 * 拦截器链
 * @author guowm
 * @date 2022/1/18
 */
public class FilterChain<T> {
    private FilterInvoker<T> header;

    public void doFilter(T context) {
        header.invoke(context);
    }

    public void setHeader(FilterInvoker<T> header) {
        this.header = header;
    }
}
