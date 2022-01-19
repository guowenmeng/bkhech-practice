package com.bkhech.home.practice.designpattern.observer;

import java.util.EventObject;

/**
 * 事件
 * @author guowm
 * @date 2022/1/19
 */
public class AnimalEvent extends EventObject {
    private Animal animal;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AnimalEvent(Object source) {
        super(source);
        this.animal = (Animal) source;
        System.out.println("event: Generate event —— " + animal.getName() + " is eating!");
    }
}
