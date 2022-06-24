package com.bkhech.home.practice.designpattern.chain_of_responsibility2;

import com.bkhech.home.practice.designpattern.chain_of_responsibility2.core.Filter;
import com.bkhech.home.practice.designpattern.chain_of_responsibility2.core.FilterInvoker;

/**
 * Filter1
 * @author guowm
 * @date 2022/1/18
 */
public class Filter1 implements Filter<A> {

    /**
     * @param context
     * @param nextFilter
     */
    @Override
    public void doFilter(A context, FilterInvoker<A> nextFilter) {
        System.out.println(context.getA());
        nextFilter.invoke(context);
    }
}
