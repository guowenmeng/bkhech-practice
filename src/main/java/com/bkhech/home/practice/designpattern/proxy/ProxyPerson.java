package com.bkhech.home.practice.designpattern.proxy;

/**
 * @author guowm
 * @date 2020/12/15
 * @description
 */
public class ProxyPerson {

    public final void speak() {
        System.out.println("speak final");
    }

    public String study() {
        System.out.println("study");
        return "study";
    }

}
