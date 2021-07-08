package com.bkhech.home.practice.javacore.core.reflection.custom_annotation;

import java.lang.annotation.*;

/**
 * 测试注解类A
 * @author guowm
 * @date 2021/7/6
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAn {

    String value() default "";

}
