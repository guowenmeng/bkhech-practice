package com.bkhech.home.practice.javacore.core.reflection.custom_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义容器注解@RepeatAns
 * @author guowm
 * @date 2021/7/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatAns {
    RepeatAn[] value();
}
