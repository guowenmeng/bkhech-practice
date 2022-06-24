package com.bkhech.home.practice.designpattern.chain_of_responsibility2.core;

/**
 * Default FilterInvoker
 * @author guowm
 * @date 2022/1/18
 */
public class DefaultFilterInvoker<T> implements FilterInvoker<T> {

    private final Filter<T> filter;
    private final FilterInvoker<T> next;

    public DefaultFilterInvoker(Filter<T> filter, FilterInvoker<T> next) {
        this.filter = filter;
        this.next = next;
    }

    /**
     * 执行
     *
     * @param context
     */
    @Override
    public void invoke(T context) {
        filter.doFilter(context, next);
    }
}
