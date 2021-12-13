package com.bkhech.home.practice.designpattern.chain_of_responsibility;

/**
 * @author guowm
 * @date 2021/12/13
 */
public class Main {
    public static void main(String[] args) {
        try {
            final ApiSignContext apiSignContext = ApiSignContext.builder().build();

            CheckSignHandler checkSignHandler = CheckSignHandler.getInstance();
            FilterChain filterChain = new FilterChain(checkSignHandler);
            filterChain.addValidationFilter(new RequestParameterFilter());
            filterChain.addValidationFilter(new RequestBodyFilter());

            filterChain.doFilter(apiSignContext, filterChain);
        } catch (SignException e) {
            throw e;
        }
    }
}
