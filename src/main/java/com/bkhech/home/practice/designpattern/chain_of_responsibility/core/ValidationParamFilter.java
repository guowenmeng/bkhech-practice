package com.bkhech.home.practice.designpattern.chain_of_responsibility.core;

/**
 * @author guowm
 * @date 2021/12/10
 */
public interface ValidationParamFilter {

    /**
     * invoke
     * @param apiSignContext
     * @param filterChain
     */
    void doFilter(ApiSignContext apiSignContext, FilterChain filterChain);
}
