package com.bkhech.home.practice.javacore.core.annotation.handle;

@OrderType
public class DefaultOrderHandler implements OrderHandler {
    @Override
    public void handle(String order) {
        System.out.println("默认订单处理器, order: " + order);
    }
}
