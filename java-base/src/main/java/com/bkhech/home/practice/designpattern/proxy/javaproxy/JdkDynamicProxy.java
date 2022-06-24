package com.bkhech.home.practice.designpattern.proxy.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * jdk dynamic proxy
 *
 * @author guowm
 * @date 2022/1/18
 */
public class JdkDynamicProxy<T> implements InvocationHandler {

    private final T target;

    public JdkDynamicProxy() {
        target = null;
    }

    public JdkDynamicProxy(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T getProxy(Class<T> proxyInterface) {
        T proxyInstance = (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{proxyInterface},
                this
        );
        return proxyInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理拦截：" + method);
        Object result = null;
        if (Modifier.isStatic(method.getModifiers()) || Objects.nonNull(target)) {
            System.out.println("执行目标方法开始");
            result = method.invoke(target, args);
            System.out.println("执行目标方法结束");
            System.out.println();
        }
        return result;
    }

}
