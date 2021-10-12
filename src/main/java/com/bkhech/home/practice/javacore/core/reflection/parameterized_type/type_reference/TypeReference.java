package com.bkhech.home.practice.javacore.core.reflection.parameterized_type.type_reference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 引用一个泛型类型
 * @param <T> 引用类型
 * @author guowm
 * @date 2021/10/12
 */
public class TypeReference<T> {
    private final Type rawType;

    protected TypeReference() {
        this.rawType = getSuperclassTypeParameter(getClass());
    }

    private Type getSuperclassTypeParameter(Class<?> clazz) {
        final Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            // try to climb up the hierarchy until meet something useful
            if (TypeReference.class != genericSuperclass) {
                return getSuperclassTypeParameter(clazz.getSuperclass());
            }
            throw new RuntimeException("'" + getClass() + "' extends TypeReference but misses the type parameter. "
                    + "Remove the extension or add a type parameter to it.");
        }

        Type rawType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
        }
        return rawType;
    }

    public final Type getRawType() {
        return this.rawType;
    }

    @Override
    public String toString() {
        return rawType.toString();
    }
}
