package com.bkhech.home.practice.designpattern.chain_of_responsibility.core;

/**
 * 从请求参数中处理
 *
 * @author guowm
 * @date 2021/12/10
 */
public class RequestParameterFilter implements ValidationParamFilter {
    /**
     * invoke
     *
     * @param apiSignContext
     * @param filterChain
     */
    @Override
    public void doFilter(ApiSignContext apiSignContext, FilterChain filterChain) {
        // 业务处理
        System.out.println("业务处理：" + this.getClass().getName());

        filterChain.doFilter(apiSignContext);
    }

}
