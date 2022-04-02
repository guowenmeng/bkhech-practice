package com.bkhech.home.practice.designpattern.chain_of_responsibility2;

import com.bkhech.home.practice.designpattern.chain_of_responsibility2.core.FilterChain;
import com.bkhech.home.practice.designpattern.chain_of_responsibility2.core.FilterChainFactory;

/**
 * 责任链测试类主入口
 * @author guowm
 * @date 2022/1/18
 */
public class Main {
    public static void main(String[] args) {
        final FilterChain<A> filterChain = FilterChainFactory.buildFilterChain(Filter1.class, Filter2.class);
        A a = new A();
        a.setA("im a");
        filterChain.doFilter(a);
    }
}
