package com.bkhech.home.practice.javacore.core.reflection.parameterized_type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型参数化类
 * @author guowm
 * @date 2021/7/6
 */
public class ParameterizedClass<T1, T2> {

    private Class<T1> entityClass;

    public ParameterizedClass(){
        Type type = getClass().getGenericSuperclass();
        System.out.println("getClass() == " + getClass());
        System.out.println("type = " + type);
        Type trueType = ((ParameterizedType)type).getActualTypeArguments()[0];
        System.out.println("trueType1 = " + trueType);
        trueType = ((ParameterizedType)type).getActualTypeArguments()[1];
        System.out.println("trueType2 = " + trueType);
        this.entityClass = (Class<T1>)trueType;
        System.out.println("entityClass = " + entityClass);

        B t = new B();
        type = t.getClass().getGenericSuperclass();

        System.out.println("A is B's super class :" + ((ParameterizedType)type).getActualTypeArguments().length);
    }

}





