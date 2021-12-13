package com.bkhech.home.practice.designpattern.chain_of_responsibility;

/**
 * 从请求体中处理
 *
 * @author guowm
 * @date 2021/12/10
 */
public class RequestBodyFilter implements ValidationParamFilter {
    /**
     * invoke
     *
     * @param apiSignContext
     * @param filterChain
     */
    @Override
    public void doFilter(ApiSignContext apiSignContext, FilterChain filterChain) {
        //业务处理
        System.out.println("业务处理：" + this.getClass());
        filterChain.doFilter(apiSignContext, filterChain);
    }

}
