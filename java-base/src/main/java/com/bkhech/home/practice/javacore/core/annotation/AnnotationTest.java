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
        // 生成并保存代理文件到本地， 是AnnotationUtils.findAnnotation 方法生成的
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Map<OrderType, OrderHandler> orderTypeContainer = new HashMap<>(2);
        PCOrderHandler pcOrderHandler = new PCOrderHandler();
        OrderType annotationPC = AnnotationUtils.findAnnotation(pcOrderHandler.getClass(), OrderType.class);
        orderTypeContainer.put(annotationPC, pcOrderHandler);

        MobileOrderHandler mobileOrderHandler = new MobileOrderHandler();
        OrderType annotationMobile = AnnotationUtils.findAnnotation(mobileOrderHandler.getClass(), OrderType.class);
        orderTypeContainer.put(annotationMobile, mobileOrderHandler);

        OrderTypeImpl orderType = new OrderTypeImpl("pc", "wechat");
        OrderHandler orderHandler = orderTypeContainer.getOrDefault(orderType, new DefaultOrderHandler());
        orderHandler.handle("1111111111");
        System.out.println("orderHandler = " + orderHandler);


    }

}

