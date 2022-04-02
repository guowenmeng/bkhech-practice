package com.bkhech.home.practice.javacore.jvm.reference;

import java.util.Date;

/**
 * @author guowm
 * @date 2022/1/24
 */
public class MyDate extends Date {

    /**
     * Creates a new instance of MyDate
     */
    public MyDate() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("obj [Date: " + this.getTime() + "] is gc");
    }

    @Override
    public String toString() {
        return "Date: " + this.getTime();
    }
}
