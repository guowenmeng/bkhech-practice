package com.bkhech.home.practice.javacore.jvm.对象内存布局;

/**
 * @author guowm
 * @date 2022/2/17
 */
public class Car {
    private int id;
    private String type;
    private double price;
    private char level;

    public Car() {
    }

    public Car(int id, String type, char level, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }
}
