package com.bkhech.home.practice.enums;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/2/22
 */
public enum PlayerHandlerType {

    /**
     * 禁言
     */
    BAN("ban"),
    
    /**
     * 封号
     */
    FROZEN("frozen");

    private final String name;

    public String getName() {
        return name;
    }

    PlayerHandlerType(String name) {
        this.name = name;
    }
}
