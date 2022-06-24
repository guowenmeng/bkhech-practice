package com.bkhech.home.practice.javacore.core.annotation.handle;

@OrderType(source = "pc", payType = "wechat")
public class PCOrderHandler implements OrderHandler {
    @Override
    public void handle(String order) {
        System.out.println("处理PC端订单, order: " + order);
    }
}
