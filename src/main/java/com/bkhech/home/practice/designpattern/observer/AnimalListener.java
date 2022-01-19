package com.bkhech.home.practice.designpattern.observer;

import java.util.EventListener;
import java.util.EventObject;

/**
 * 事件监听器
 * @author guowm
 * @date 2022/1/19
 */
public interface AnimalListener extends EventListener {
    void eventHandler(EventObject eventObject);
}
