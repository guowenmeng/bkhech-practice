package com.bkhech.home.practice.javacore.core.reflection.parameterized_type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 泛型参数化类
 * 1. getGenericSuperclass()和getSuperclass()区别：
 *   相同点：都返回当前对象所表示的类的超类。
 *   不同点：`getGenericSuperclass` 会包含该超类的泛型。
 * 2. getGenericInterfaces()和getInterfaces()区别：
 *   和 1 相同
 * @author guowm
 * @date 2021/7/6
 */
public class ParameterizedClass<T1, T2> {
    private Class<T1> entityClass1;
    private Class<T2> entityClass2;

    public ParameterizedClass() {
        System.out.println("getClass() == " + getClass());
        Type type = getClass().getGenericSuperclass();
        System.out.println("genericSuperclassType = " + type);

        Type superclassType = getClass().getSuperclass();
        System.out.println("superclassType = " + superclassType);

        // 返回表示某些接口的 Type，这些接口由此对象所表示的类或接口 直接实现， 如果没有返回空数组
        final Type[] genericInterfaces = getClass().getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println("genericInterface = " + genericInterface);
        }

        // 如果没有返回空数组
        final Class<?>[] interfaces = getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println("anInterface = " + anInterface);
        }

        Type[] actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            switch (i) {
                case 0:
                    this.entityClass1 = (Class<T1>)actualTypeArguments[i];
                    break;
                case 1:
                    this.entityClass2 = (Class<T2>)actualTypeArguments[i];
                    break;
                default:
            }
        }
        System.out.println("entityClass1 = " + entityClass1);
        System.out.println("entityClass2 = " + entityClass2);

        B t = new B();
        type = t.getClass().getGenericSuperclass();
        System.out.println("A is B's super class :" + ((ParameterizedType)type).getActualTypeArguments().length);
    }

}





