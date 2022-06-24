package com.bkhech.home.practice.javacore.core.reflection.inherited_anno;

import java.lang.annotation.*;

/**
 * 自定义不可继承注解
 * @author guowm
 * @date 2021/7/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnheritableAn {
}
