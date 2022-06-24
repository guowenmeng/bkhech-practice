package com.bkhech.home.practice.javacore.core.reflection;

import com.bkhech.home.practice.javacore.core.reflection.custom_annotation.RepeatAn;
import com.bkhech.home.practice.javacore.core.reflection.custom_annotation.RepeatAns;
import com.bkhech.home.practice.javacore.core.reflection.custom_annotation.TestAn;
import com.bkhech.home.practice.javacore.core.reflection.inherited_anno.InheritableAn;
import com.bkhech.home.practice.javacore.core.reflection.inherited_anno.UnOrInheritableDemo;
import com.bkhech.home.practice.javacore.core.reflection.inherited_anno.UnheritableAn;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

/**
 * 反射样例
 * @author guowm
 * @date 2021/7/6
 * {@literal https://blog.csdn.net/beFocused/article/details/104785188?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242}
 */
@TestAn("ReflectionDemo")
public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchMethodException {
//        arrayInstanceDemo();
//        classDemo();
//        annotationDemo();
//        methodDemo();
        fieldDemo();
    }

    private static String shareField;
    private String field;

    @TestAn("ReflectionDemo#testMethod")
    @RepeatAn()
    @RepeatAn()
    public void testMethod(String s) {

    }

    public void testMethodPublic(){}

    protected void testMethodProtected(){}

    private void testMethodPrivate(){}

    void testMethodDefault(){}

    private interface InnerInterface {

    }

    public static class InnerClass {

    }

    /**
     *  java的反射包中获取一个元素(Class、 Method、Constructor、Field)上的注解对象主要有这6个方法
     *  getAnnotation(Class<T> annotationClass)、getAnnotations()、
     *  getDeclaredAnnotation(Class<T> annotationClass)、getDeclaredAnnotations()、
     *  getAnnotationsByType(Class<T> annotationClass)、getDeclaredAnnotationsByType(Class<T> annotationClass)。
     *  这些方法获取的都是运行时注解。
     *
     * 在介绍这6个方法前，先描述下几个概念（翻译自jdk的注释）：
     *
     * directly present："直接修饰"注解是指元素的RuntimeVisibleAnnotations、RuntimeVisibleParameterAnnotations
     * 或RuntimeVisibleTypeAnnotations属性包含的注解(RuntimeVisibleAnnotations、RuntimeVisibleParameterAnnotations、
     * RuntimeVisibleTypeAnnotations是classfile结构的属性)，
     * 说白了就是指直接修饰在某个元素上的注解；
     * indirectly present："间接修饰"注解就是指得容器注解的数组中指定的注解；
     * present：并不是"直接修饰"注解和"间接修饰"注解的合集，而是"直接修饰"注解和父类继承下来的"直接注解"的合集；
     * associated："关联"是"直接修饰"注解、"间接修饰"注解以及父类继承下来的注解的合集；
     *
     * 理解了这几个概念后，以上6个方法返回的注解对象可用下表来表示
     *
     * 方法	                        directly-present	indirectly-present	  present	 associated
     * getAnnotation	 	 	                                                 *
     * getAnnotations	 	 	                                                 *
     * getAnnotationsByType	 	          	 	                                              *
     * getDeclaredAnnotation	           *
     * getDeclaredAnnotations	           *
     * getDeclaredAnnotationsByType	       *	                *
     *
     */
    private static void annotationDemo() throws NoSuchMethodException {
        //{$Proxy1@821}@com.bkhech.home.practice.javacore.core.reflection.custom_annotation.TestAnnotationA(value=A)
        //annotation是jdk底层基于JDK Proxy产生的动态代理类
        final TestAn annotationA = UnOrInheritableDemo.class.getAnnotation(TestAn.class);
        System.out.println("annotationA: " + Objects.nonNull(annotationA));
        //isAnnotationPresent底层调用的是getAnnotation方法，InheritableDemo类继承父类Base
        final InheritableAn annotationInheritableAn = UnOrInheritableDemo.class.getAnnotation(InheritableAn.class);
        System.out.println("annotationInheritableAn: " + Objects.nonNull(annotationInheritableAn));
        final UnheritableAn annotationUnheritableAn = UnOrInheritableDemo.class.getAnnotation(UnheritableAn.class);
        System.out.println("annotationUnheritableAn: " + Objects.isNull(annotationUnheritableAn));

        final TestAn[] annotationsByTypeTestAn = UnOrInheritableDemo.class.getAnnotationsByType(TestAn.class);
        Arrays.stream(annotationsByTypeTestAn).forEach(item -> System.out.println("annotationsByTypeTestAn: " + Objects.nonNull(item)));
        final InheritableAn[] annotationsByTypeInheritableAn = UnOrInheritableDemo.class.getAnnotationsByType(InheritableAn.class);
        Arrays.stream(annotationsByTypeInheritableAn).forEach(item -> System.out.println("annotationsByTypeInheritableAn: " + Objects.nonNull(item)));
        final UnheritableAn[] annotationsByTypeUnheritableAn = UnOrInheritableDemo.class.getAnnotationsByType(UnheritableAn.class);
        Arrays.stream(annotationsByTypeUnheritableAn).forEach(item -> System.out.println("annotationsByTypeUnheritableAn: " + Objects.isNull(item)));





        final TestAn declaredAnnotationA = UnOrInheritableDemo.class.getDeclaredAnnotation(TestAn.class);
        System.out.println("declaredAnnotationA: " + Objects.nonNull(declaredAnnotationA));
        //isAnnotationPresent底层调用的是getAnnotation方法，InheritableDemo类继承父类Base
        final InheritableAn declaredAnnotationInheritableAn = UnOrInheritableDemo.class.getDeclaredAnnotation(InheritableAn.class);
        System.out.println("declaredAnnotationInheritableAn: " + Objects.isNull(declaredAnnotationInheritableAn));
//        final UnheritableAn declaredAnnotationUnheritableAn = UnOrInheritableDemo.class.getDeclaredAnnotation(UnheritableAn.class);
//        System.out.println("declaredAnnotationUnheritableAn: " + Objects.isNull(declaredAnnotationUnheritableAn));

        final TestAn[] declaredAnnotationsByTypeTestAn = UnOrInheritableDemo.class.getDeclaredAnnotationsByType(TestAn.class);
        Arrays.stream(declaredAnnotationsByTypeTestAn).forEach(item -> System.out.println("declaredAnnotationsByTypeTestAn: " + Objects.nonNull(item)));
        final InheritableAn[] declaredAnnotationsByTypeInheritableAn = UnOrInheritableDemo.class.getDeclaredAnnotationsByType(InheritableAn.class);
        Arrays.stream(declaredAnnotationsByTypeInheritableAn).forEach(item -> System.out.println("declaredAnnotationsByTypeInheritableAn: " + Objects.isNull(item)));
//        final UnheritableAn[] declaredAnnotationsByTypeUnheritableAn = UnOrInheritableDemo.class.getDeclaredAnnotationsByType(UnheritableAn.class);
//        Arrays.stream(declaredAnnotationsByTypeUnheritableAn).forEach(item -> System.out.println("declaredAnnotationsByTypeUnheritableAn: " + Objects.isNull(item)));




        Method method = ReflectionDemo.class.getDeclaredMethod("testMethod", String.class);

        final RepeatAn[] declaredAnnotationsByTypeRepeatAn = method.getDeclaredAnnotationsByType(RepeatAn.class);
        Arrays.stream(declaredAnnotationsByTypeRepeatAn).forEach(item -> System.out.println("declaredAnnotationsByTypeRepeatAn: " + Objects.nonNull(item)));
        final RepeatAns[] declaredAnnotationsByTypeRepeatAns = method.getDeclaredAnnotationsByType(RepeatAns.class);
        Arrays.stream(declaredAnnotationsByTypeRepeatAns).forEach(item -> System.out.println("declaredAnnotationsByTypeRepeatAns: " + Objects.nonNull(item)));

    }

    /**
     * 方法含义
     * getDeclaringClass
     * getDeclaredClasses
     * getClasses
     */
    private static void classDemo() {
        // 1.该方法返回一个Class对象，返回当前class对象的声明对象class,一般针对内部类的情况，比如A类有内部类B，
        // 那么通过B.class.getDeclaringClass()方法将获取到A的Class对象.
        // 2.在使用反射对象时比如Method和Field的getDeclaringClass方法将获取到所属类对象
        final Class<?> declaringClass = InnerClass.class.getDeclaringClass();
        // ReflectionDemo
        System.out.println("declaringClass: " + declaringClass.getSimpleName());

        // 该方法返回当前Class声明的public，private ，default，private的内部类和内部接口
        final Class<?>[] declaredClasses = ReflectionDemo.class.getDeclaredClasses();
        //ReflectionDemo$InnerClass ReflectionDemo$InnerInterface
        Arrays.stream(declaredClasses).forEach(item -> System.out.println("declaredClasses: " + item.getSimpleName()));

        // 该方法返回当前Class声明的public的内部类和内部接口，注意是public的
        final Class<?>[] classes = ReflectionDemo.class.getClasses();
        //ReflectionDemo$InnerClass
        Arrays.stream(classes).forEach(item -> System.out.println("classes: " + item.getSimpleName()));
    }

    /**
     * 数组实例化方式样例
     *  1. class.getComponentType()，返回表示数组组件类型的 Class。如果此类不表示数组类，则此方法返回 null
     */
    private static void arrayInstanceDemo() {
        final Class<String> strClass = String.class;
        final Class<?> strComponentType = strClass.getComponentType();
        // strClass不是数组类型，所以返回的strComponentType == null
        System.out.println("strComponentType:" + strComponentType == null);

        final Class<String[]> strArrClass = String[].class;
        final Class<?> strArrComponentType = strArrClass.getComponentType();
        // strArrClass是数组类型，所以返回的strArrComponentType != null
        System.out.println("strArrComponentType:" + strArrComponentType != null);
        // 实例化strArrComponentType类型，长度为1 的数组实例
        Object arrInstance = Array.newInstance(strArrComponentType, 1);
        // 赋值
        Array.set(arrInstance, 0, "2");
        System.out.println("arrInstance:" + arrInstance);
    }

    /**
     * 方法样例
     */
    private static void methodDemo() {
        final Class<ReflectionDemo> reflectionDemoClass = ReflectionDemo.class;
        System.out.println("------------getMethods-------包括父类在内的所有 public 方法---------");
        Arrays.stream(reflectionDemoClass.getMethods()).forEach(System.out::println);
        System.out.println("------------getDeclaredMethods------当前类声明的所有方法----------");
        Arrays.stream(reflectionDemoClass.getDeclaredMethods()).forEach(System.out::println);
        System.out.println("------------getEnclosingMethod-----啥意思？？？---------");
        System.out.println(reflectionDemoClass.getEnclosingMethod());
    }

    /**
     * 属性样例
     * 注意：
     * 字段不是静态字段的话,要传入反射类的对象。如果传 null 是会报 java.lang.NullPointerException，
     * 但是如果字段是静态字段的话，传入任何对象都是可以的, 包括 null
     */
    private static void fieldDemo() {
        final ReflectionDemo reflectionDemo = new ReflectionDemo();
        ReflectionDemo.shareField = "aa";
        reflectionDemo.field = "bb";

        final Class<ReflectionDemo> reflectionDemoClass = ReflectionDemo.class;
        final Field[] declaredFields = reflectionDemoClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                Object fieldValue = null;
                final int modifiers = declaredField.getModifiers();
                if (Modifier.isStatic(modifiers)) {
//                    fieldValue = declaredField.get(reflectionDemo); // 静态方法，执行这样用也没问题
                    fieldValue = declaredField.get(null);
                } else {
//                    fieldValue = declaredField.get(null); // 非静态方法，空指针
                    fieldValue = declaredField.get(reflectionDemo);
                }
                System.out.println(fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }
}
