package com.bkhech.home.practice.javacore.core.annotation;

import com.bkhech.home.practice.javacore.core.annotation.handle.*;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guowm
 * @date 2020/7/27
 * @description
 */
public class AnnotationTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Map<OrderType, OrderHandler> map = new HashMap<>(2);
        PCOrderHandler pcOrderHandler = new PCOrderHandler();
        OrderType annotationPC = AnnotationUtils.findAnnotation(pcOrderHandler.getClass(), OrderType.class);
        map.put(annotationPC, pcOrderHandler);

        MobileOrderHandler mobileOrderHandler = new MobileOrderHandler();
        OrderType annotationMobile = AnnotationUtils.findAnnotation(mobileOrderHandler.getClass(), OrderType.class);
        map.put(annotationMobile, mobileOrderHandler);

        OrderTypeImpl orderType = new OrderTypeImpl("pc", "wechat");
        OrderHandler orderHandler = map.get(orderType);
        orderHandler.handle("");
        System.out.println("orderHandler = " + orderHandler);


    }

}

