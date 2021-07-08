package com.bkhech.home.practice.algorithm.hashedwheeltimer;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author guowm
 * @date 2021/7/7
 */
@Data
public class Invocation {
    private Object target;
    private Method method;
    private Object[] parameterValues;

    public Invocation(Object target, Method method, Object[] parameterValues) {
        this.target = target;
        this.method = method;
        this.parameterValues = parameterValues;
    }
}
