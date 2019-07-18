package com.bkhech.home.practice.javacore.java8;

import com.bkhech.home.practice.entity.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/7/16
 * 分组
 */
public class GroupByTest {

    /**
     * java8 stream 如何按多字段分组，并对一个字段求和
     */
    public static void groupByAndSum() {
        ArrayList<User> users = new ArrayList();
        users.add(new User("tom", "bb", "cc", 100L));
        users.add(new User("tom", "bb", "cc", 50L));
        users.add(new User("jerry", "dd", "ee", 30L));
        users.add(new User("jerry", "dd", "ee",40L));

        users.stream()
                .collect(Collectors
                        .groupingBy(
                                user -> new User(user.getName(), user.getPhone(), user.getAddress()),
                                Collectors.summarizingLong(user -> user.getScope())
                        )
                )
                .forEach((k,v) -> {
                    k.setScope(v.getSum());
                    System.out.println(k);
                });
    }

    public static void main(String[] args) {
        groupByAndSum();
    }
}
