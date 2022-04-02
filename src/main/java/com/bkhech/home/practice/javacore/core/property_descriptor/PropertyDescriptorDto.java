package com.bkhech.home.practice.javacore.core.property_descriptor;

/**
 * PropertyDescriptor Bean
 * @author guowm
 * @date 2022/4/2
 */
public class PropertyDescriptorDto {
    private Integer name;

    public Integer getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(Integer name) {
        System.out.println("setName");
        this.name = name;
    }
}
