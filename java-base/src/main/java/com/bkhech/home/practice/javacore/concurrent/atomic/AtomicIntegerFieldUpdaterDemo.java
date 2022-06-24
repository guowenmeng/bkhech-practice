package com.bkhech.home.practice.javacore.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author guowm
 * @date 2021/2/26
 * @see AtomicIntegerFieldUpdater
 * @see AtomicLongFieldUpdater
 * @see AtomicReferenceFieldUpdater
 * @description
 */
public class AtomicIntegerFieldUpdaterDemo {

    /**
     * 创建原子更新器，并设置需要更新的对象类和对象的属性
     */
    private static final AtomicIntegerFieldUpdater<User> integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
    private static final AtomicReferenceFieldUpdater<ReferenceUser, Integer> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(ReferenceUser.class, Integer.class, "old");

    public static void main(String[] args) {

//        testAtomicIntegerFieldUpdater();
        testAtomicReferenceFieldUpdater();
    }

    public static void testAtomicReferenceFieldUpdater() {
        // 实际可设置为共享变量
        ReferenceUser conan = new ReferenceUser("conan", 10);
        System.out.println("现在年龄1：" + referenceFieldUpdater.get(conan));
        System.out.println("result：" + referenceFieldUpdater.compareAndSet(conan, 10, 11));
        System.out.println("现在年龄：" + referenceFieldUpdater.get(conan));
    }

    public static void testAtomicIntegerFieldUpdater() {
        User conan = new User("conan", 10);
        System.out.println("之前年龄：" + integerFieldUpdater.getAndIncrement(conan));
        System.out.println("现在年龄：" + integerFieldUpdater.get(conan));
    }

    public static class User {
        private final String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }

    public static class ReferenceUser {
        private final String name;
        public volatile Integer old;

        public ReferenceUser(String name, Integer old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public Integer getOld() {
            return old;
        }
    }

}
