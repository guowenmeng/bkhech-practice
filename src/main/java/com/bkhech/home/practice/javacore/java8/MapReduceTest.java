package com.bkhech.home.practice.javacore.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author guowm
 * @date 2020/9/28
 * @description
 */
public class MapReduceTest {

    public static void main(String[] args) {

//        reduce1();

//        reduce2();

        reduce3();
    }

    private static void reduce1() {
        List<Integer> num = Arrays.asList(1, 2, 4, 5, 6, 7);
        Integer result = num.stream().reduce((x, y) -> {
            System.out.println("x:"+x);
            return x + y;
        }).get();
        System.out.println("result:"+result);
        //你也可以这样写，效果一样，一个为Lambda表达式，一个匿名内部类
        Integer integer = num.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                System.out.println("a = " + a);
                return a + b;
            }
        }).get();

        System.out.println("integer = " + integer);
    }

    private static void reduce2() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6);
        // identity： initial value
        Integer result = num.stream().reduce(0,(x, y) -> {
            System.out.println("x:" + x);
            return x + y;
        });
        System.out.println("result:" + result);
    }

    private static void reduce3() {
        //非并行:向ArrayList添加数据：
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6);
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(7);

        List<Integer> reduce = num.stream().reduce(arr, (x, y) -> {
            x.add(y);
            return x;
        }, (List<Integer> x, List<Integer> y) -> {
            System.out.println("并行才会出现");
            return x;
        });
        System.out.println(reduce);

        //并行：集合内数据求和：
        List<Integer> num2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer num1 = num2.parallelStream().reduce(7, (x, y) -> x + y, (x, y)->{
            System.out.println("这里调用一次");
            return x + y;
        });
        System.out.println(num1);
    }







}
