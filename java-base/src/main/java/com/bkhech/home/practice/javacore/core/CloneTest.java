package com.bkhech.home.practice.javacore.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guowm
 * @date 2020/1/2
 * @description
 *
 * 浅拷贝：浅拷贝是按位拷贝对象，它会创建一个新对象，这个对象有着原始对象属性值的一份精确拷贝。
 *          如果属性是基本类型，拷贝的就是基本类型的值；如果属性是内存地址（引用类型），拷贝的就是内存地址 ，
 *          因此如果其中一个对象改变了这个地址，就会影响到另一个对象。
 *          对象、数组（默认实现了Cloneable接口）、集合 都是浅拷贝。
 * 深拷贝：需要创建拷贝类的一个对象。
 *          序列化 属于深拷贝。 缺点明显： 1. transient修饰的属性无法序列化；2. 性能低效（大概是Cloneable效率的100倍）
 *
 * 推荐实现方式： 实现了cloneable接口， 重写父类的clone()方法。针对深浅拷贝只是clone()方式实现的方式不同；
 *
 *   浅拷贝：super.clone();
 *   深拷贝： 需要创建拷贝类的一个对象；
 */
public class CloneTest {

    public static void main(String[] args) {
        //1. simple object
//        Person a = new Person("ll");
//        Person b = a.clone();
//
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
//
//        b.setName("kk");
//
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);

        //2. complex object

        Person person = new Person("ll");

        PersonComplex personComplex = new PersonComplex("ll", Arrays.asList(person));
        PersonComplex personComplex2 = personComplex.clone();

        System.out.println("personComplex = " + personComplex);
        System.out.println("personComplex2 = " + personComplex2);

        personComplex.setName("kk");
        personComplex.getHouses().get(0).setName("kk");

        System.out.println("personComplex = " + personComplex);
        System.out.println("personComplex2 = " + personComplex2);

    }

}

@Data
class Person implements Cloneable{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    protected Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor
class PersonComplex implements Cloneable{
    private String name;

    private List<Person> houses;

    @Override
    protected PersonComplex clone() {

        //浅拷贝: 直接调用父类的clone()方法
       /* try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }*/


        // 深拷贝: 需要创建拷贝类的一个对象
        PersonComplex clone = new PersonComplex();
        clone.setName(this.name);

        List<Person> list = new ArrayList<>(this.houses.size());
        this.houses.stream().forEach(item -> {
            list.add(new Person(item.getName()));
        });

        clone.setHouses(list);
        return clone;
    }


}