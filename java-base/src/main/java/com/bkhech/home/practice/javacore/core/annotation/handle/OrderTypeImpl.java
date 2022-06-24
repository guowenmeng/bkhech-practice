package com.bkhech.home.practice.javacore.core.annotation.handle;

import java.lang.annotation.Annotation;

/**
 * @author guowm
 * @date 2020/7/27
 * @description
 */
public class OrderTypeImpl implements OrderType {
    private final String source;
    private final String payType;

    public OrderTypeImpl(String source, String payType) {
        this.source = source;
        this.payType = payType;
    }

    @Override
    public String source() {
        return this.source;
    }

    @Override
    public String payType() {
        return this.payType;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return OrderType.class;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();
        hashCode += (127 * "payType".hashCode()) ^ payType.hashCode();
        return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderType)) {
            return false;
        }
        OrderType other = (OrderType) obj;
        return source.equals(other.source()) && payType.equals(other.payType());
    }

}
