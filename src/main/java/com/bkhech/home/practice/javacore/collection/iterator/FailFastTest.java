package com.bkhech.home.practice.javacore.collection.iterator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guowm
 * @date 2019/9/10
 * @description
 * 快速失败
 */
public class FailFastTest {

    public static void main(String[] args) {
        //ConcurrentHashMap 不会快速失败
//        Map<String, String> m = new ConcurrentHashMap<>();
        Map<String, String> m = new HashMap<>();
        m.put("A", "AA");
        m.put("B", "BB");
        m.put("C", "CC");

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            m.put("D", "DD");

            Map.Entry<String, String> e = (Map.Entry) it.next();
            System.out.println(e.getKey() + e.getValue());
        }
    }

}
