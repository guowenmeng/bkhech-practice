package com.bkhech.home.practice.designpattern.chain_of_responsibility.core;

/**
 * 职责链
 * @author guowm
 * @date 2021/12/10
 */
public interface FilterChain {

    /**
     * 执行职责链
     * @param apiSignContext
     */
    void doFilter(ApiSignContext apiSignContext);
}
