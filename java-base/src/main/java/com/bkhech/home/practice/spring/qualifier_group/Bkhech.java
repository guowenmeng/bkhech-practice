package com.bkhech.home.practice.spring.qualifier_group;

/**
 * @author guowm
 * @date 2021/12/13
 */
public class Bkhech {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bkhech{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
