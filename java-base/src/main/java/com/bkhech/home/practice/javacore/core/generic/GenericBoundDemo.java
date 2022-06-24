package com.bkhech.home.practice.javacore.core.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型边界样例
 * <p>
 * 1.
 * <? extends E> 是 Upper Bound（上限） 的通配符，用来限制元素的类型的上限。
 * 不能往里存(不能使用add方法，但可以直接给List<? extends T>对象赋值，即初始化)，只能往外取，即一旦初始化就不能修改
 * <? super E> 是 Lower Bound（下限） 的通配符 ，用来限制元素的类型下限。能往里存，也能往外取，但取的类型是Object
 * <p>
 *
 * <p>
 * 2. PECS法则
 *
 * PECS法则：生产者（Producer）使用extends，消费者（Consumer）使用super
 * 1、生产者
 * 如果你需要一个提供E类型元素的集合，使用泛型通配符<? extends E>。它好比一个生产者，可以提供数据。
 * 2、消费者
 * 如果你需要一个只能装入E类型元素的集合，使用泛型通配符<? super E>。它好比一个消费者，可以消费你提供的数据。
 * 3、既是生产者也是消费者
 * 既要存储又要读取，那就别使用泛型通配符。
 * <p>
 *
 * 3. 为什么要引入泛型通配符？一句话：为了保证类型安全。
 *
 * @author guowm
 * @date 2021/8/6
 * {@literal https://blog.csdn.net/qq_41135605/article/details/108983226?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control}
 * {@literal https://blog.csdn.net/jdsjlzx/article/details/70479227?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control}
 */
public class GenericBoundDemo {
    public void main() {
//        test1();
        test2();
    }

    private static void test1() {
        //表示集合中的元素类型 上限 为Person类型，即只能是Person或者Person的子类
        //ArrayList<? extends Person> 表示 “具有任何从Person继承类型的列表”，编译器无法确定List所持有的类型，所以无法安全的向其中添加对象
        ArrayList<? extends Person> producerList = new ArrayList<>();
        if (!producerList.isEmpty()) {
            final Person person = producerList.get(0);
        }

        //表示集合中元素类型 下限 为Person类型，即只能是Person或Person的父类
        ArrayList<? super Person> consumerList = new ArrayList<>();
//        consumerList.add(new Animal()); //编译不通过
        consumerList.add(new Person());
        consumerList.add(new ManTeacher());

        if (!consumerList.isEmpty()) {
            final Object object = consumerList.get(0);
        }
    }

    private static void test2() {
        //getPersonList方法会返回一个Person的子类的list
        final List<? extends Person> personList = getPersonList();
    }

    private static List<? extends Person> getPersonList() {
        //TODO 可以使用策略模式获取数据
        return new ArrayList<>();
    }

    static class Animal {
    }

    static class Person extends Animal {
    }

    static class Man extends Person {
    }

    static class ManTeacher extends Man {
    }

}
