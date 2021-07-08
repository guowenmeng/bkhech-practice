package com.bkhech.home.practice.javacore.core.reflection.custom_annotation;

import java.lang.annotation.*;

/**
 * 自定义可重复的注解@RepeatAn
 * @author guowm
 * @date 2021/7/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatAns.class)
public @interface RepeatAn {
}
