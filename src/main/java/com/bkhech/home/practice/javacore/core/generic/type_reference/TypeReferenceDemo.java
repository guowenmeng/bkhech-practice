package com.bkhech.home.practice.javacore.core.generic.type_reference;

import java.lang.reflect.Type;

/**
 * 泛型参数使用样例
 * @author guowm
 * @date 2021/10/12
 */
public class TypeReferenceDemo {

    public static void main(String[] args) {
        TypeReferenceBasicObject<Double> typeReferenceBasicObject = new TypeReferenceBasicObject<>();
        final Type basicObjectRawType = typeReferenceBasicObject.getRawType();
        System.out.println("basicObjectRawType = " + basicObjectRawType);

        TypeReferenceComplexObject<Double> typeReferenceComplexObject = new TypeReferenceComplexObject<>();
        final Type complexObjectRawType = typeReferenceComplexObject.getRawType();
        // class com.bkhech.home.practice.javacore.core.generic.type_reference.ComplexObject
        // 无法获取到 ComplexObject 泛型参数的引用
        System.out.println("complexObjectRawType = " + complexObjectRawType);
    }

}
