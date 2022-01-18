package com.bkhech.home.practice.designpattern.chain_of_responsibility;

import com.bkhech.home.practice.designpattern.chain_of_responsibility.core.*;

/**
 * @author guowm
 * @date 2021/12/13
 */
public class Main {
    public static void main(String[] args) {
        try {
            final ApiSignContext apiSignContext = ApiSignContext.builder().build();

            CheckSignHandler checkSignHandler = CheckSignHandler.getInstance();
            ApplicationFilterChain filterChain = new ApplicationFilterChain(checkSignHandler);
            filterChain.addFilter(new RequestParameterFilter());
            filterChain.addFilter(new RequestBodyFilter());

            filterChain.doFilter(apiSignContext);
        } catch (SignException e) {
            throw e;
        }
    }
}
