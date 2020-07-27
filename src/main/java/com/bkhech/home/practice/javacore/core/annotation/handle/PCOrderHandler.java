package com.bkhech.home.practice.javacore.core.annotation.handle;

import org.springframework.stereotype.Component;

@Component
@OrderType(source = "pc", payType = "wechat")
public class PCOrderHandler implements OrderHandler {
    @Override
    public void handle(String order) {
        System.out.println("处理PC端订单");
    }
}
