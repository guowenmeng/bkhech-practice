package com.bkhech.home.practice.javacore.core.annotation.handle;

import org.springframework.stereotype.Component;

@Component
@OrderType(source = "mobile", payType = "wechat")
public class MobileOrderHandler implements OrderHandler {
    @Override
    public void handle(String order) {
        System.out.println("处理移动端订单");
    }
}
