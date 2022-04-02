package com.bkhech.home.practice.designpattern.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author guowm
 * @date 2020/12/15
 * @description
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("There's enhancer of " + method.getName() + " method");
        Object invokeResult = methodProxy.invokeSuper(o, objects);
        return invokeResult;
    }
}
