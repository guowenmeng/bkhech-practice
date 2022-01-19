package com.bkhech.home.practice.designpattern.observer;

import java.util.Objects;

/**
 * animal 处理类
 * @author guowm
 * @date 2022/1/19
 */
public class AnimalHandler {
    private final Animal animal;
    private AnimalListener animalListener;

    public AnimalHandler(Animal animal) {
        this.animal = animal;
    }

    public void addAnimalListener(AnimalListener animalListener) {
        this.animalListener = animalListener;
    }

    public void eat() {
        System.out.println("event source: Trigger event —— " + animal.getName() + " will eat!");
        if (Objects.nonNull(animalListener)) {
            animalListener.eventHandler(new AnimalEvent(animal));
        }
    }
}
