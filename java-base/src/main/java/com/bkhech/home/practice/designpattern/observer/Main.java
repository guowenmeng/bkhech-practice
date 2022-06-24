package com.bkhech.home.practice.designpattern.observer;

import java.util.EventObject;

/**
 * 观察者模式测试入口类
 * @author guowm
 * @date 2022/1/19
 */
public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setName("lion");
        final AnimalHandler animalHandler = new AnimalHandler(animal);
        animalHandler.addAnimalListener(new AnimalListener() {
            @Override
            public void eventHandler(EventObject eventObject) {
                // 从事件中获取事件源
                Animal source = (Animal) eventObject.getSource();
                // 打印事件源的名称
                System.out.println("listener: Get event —— " + source.getName() + " is eating!");
            }
        });
        animalHandler.eat();
    }
}
