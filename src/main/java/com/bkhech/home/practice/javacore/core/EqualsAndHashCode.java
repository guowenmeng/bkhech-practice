package com.bkhech.home.practice.javacore.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsAndHashCode {

    @Data
    @AllArgsConstructor
    private class A {
        private int id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            A a = (A) o;
            return id == a.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public void test() {
        Set<A> set = new HashSet<>();
        A xiaoguo = new A(1, "xiaoguo");
        set.add(xiaoguo);

        //修改
        xiaoguo.setId(2);
//        xiaoguo.setName("xiaoli");

        System.out.println("set.contains(xiaoguo) = " + set.contains(xiaoguo));

        //在将元素假如到set中后，在修改元素的属性之后，使用remove方法，删不掉
        System.out.println("set.remove(xiaoguo) = " + set.remove(xiaoguo));
        System.out.println("after remove set.size() = " + set.size());
        set.clear();
        System.out.println("after clear set.size() = " + set.size());

    }

    public static void main(String[] args) {
        new EqualsAndHashCode().test();
    }

}
