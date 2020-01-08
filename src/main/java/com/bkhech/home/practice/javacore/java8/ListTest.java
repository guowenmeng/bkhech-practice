package com.bkhech.home.practice.javacore.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author guowm
 * @date 2020/1/3
 * @description
 *
 * https://juejin.im/post/5b6d801af265da0f926bb2a2
 */
public class ListTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("1","2","3");

        Optional<Integer> reduceOptional = list.stream().map(Integer::parseInt).reduce((a, b) -> a + b);
        Integer reduce = list.stream().map(Integer::parseInt).reduce(0, (a, b) -> a + b);
        if (reduceOptional.isPresent()) {
            System.out.println(reduceOptional.get());
        }

        System.out.println(reduce);


    }

}
