package com.bkhech.home.practice.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2018/12/18
 */
public class MapFlatMapTest {

    @Test
    public void testTest() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        //Arrays.stream接收一个数组返回一个流
        List<String> collect1 = words.stream()
                .map(str -> str.split(""))
                .flatMap(str1 -> Arrays.stream(str1))
                .collect(Collectors.toList());

        collect1.forEach(System.out::println);


        List<List<String>> lists = Arrays.asList(Arrays.asList("Jordan"),
                Arrays.asList("Kobe","James"),Arrays.asList("Durant","Curry")
        );

        //集合 -> 流：流里的元素也是集合的元素，所以流的元素是List<String>
        Stream<List<String>> streamRaw = lists.stream();
        //流元素的转换，List<String>，通过Collection的stream方法，变为Stream<String>
        //即：List<String> -> Stream<String>，把List<String>进行了一次流化
        Stream<Stream<String>> streamMap = lists.stream().map(Collection::stream);
        //和上面方法引用效果一样
//        Stream<Stream<String>> streamMap2 = streamRaw.map(l -> l.stream());

        //map方法是单纯的转换，而flatMap方法可以进行流的扁平化，和上面的map做对比
        Stream<String> streamFlatmap = lists.stream().flatMap(l -> l.stream());


        streamRaw.forEach(System.out::println);
        streamFlatmap.forEach(System.out::println);


    }



}
