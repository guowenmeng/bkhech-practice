package com.bkhech.home.practice.designpattern.chain_of_responsibility2.core;

/**
 * 责任链模式工厂
 *
 * @author guowm
 * @date 2022/1/18
 */
public class FilterChainFactory {
    public static <T> FilterChain<T> buildFilterChain(Class<? extends Filter<T>>... filterClsList) {
        FilterInvoker<T> last = new FilterInvoker<T>() {
        };
        FilterChain<T> filterChain = new FilterChain<>();
        for (int i = filterClsList.length - 1; i >= 0; i--) {
            FilterInvoker<T> next = last;
            Filter<T> filter = newInstance(filterClsList[i]);
//            last = new FilterInvoker<T>() {
//                @Override
//                public void invoke(T context) {
//                    filter.doFilter(context, next);
//                }
//            };
            last = new DefaultFilterInvoker<>(filter, next);
        }
        filterChain.setHeader(last);
        return filterChain;
    }

    private static <T> Filter<T> newInstance(Class<? extends Filter<T>> filterCls) {
        Filter<T> filter = null;
        try {
            filter = filterCls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return filter;
    }
}
