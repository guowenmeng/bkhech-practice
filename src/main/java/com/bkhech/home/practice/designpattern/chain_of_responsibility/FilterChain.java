package com.bkhech.home.practice.designpattern.chain_of_responsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guowm
 * @date 2021/12/10
 */
public class FilterChain implements ValidationParamFilter {

    private int pos = 0;
    private List<ValidationParamFilter> validationParamFilters;

    private final CheckSignHandler checkSignHandler;

    public FilterChain(CheckSignHandler checkSignHandler) {
        this.checkSignHandler = checkSignHandler;
    }

    public void addValidationFilter(ValidationParamFilter validationParamFilter) {
        if (validationParamFilters == null) {
            validationParamFilters = new ArrayList<>();
        }
        validationParamFilters.add(validationParamFilter);
    }

    @Override
    public void doFilter(ApiSignContext apiSignContext, FilterChain filterChain) {
        // 所有过滤器执行完毕
        if (pos == validationParamFilters.size()) {
            checkSignHandler.checkSign(apiSignContext.getSign(), apiSignContext.getParamMap());
            return;
        }
        validationParamFilters.get(pos++).doFilter(apiSignContext, filterChain);
    }
}
