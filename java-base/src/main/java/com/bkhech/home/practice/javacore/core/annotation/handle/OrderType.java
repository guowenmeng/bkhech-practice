package com.bkhech.home.practice.javacore.core.annotation.handle;

import java.lang.annotation.*;

/**
 * @author guowm
 * @date 2020/7/27
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderType {

    String source() default "";
    String payType() default "";
}
