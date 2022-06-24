package com.bkhech.home.practice.spring.qualifier_group;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author guowm
 * @date 2021/12/13
 */
@ComponentScan("com.bkhech.home.practice.spring.qualifier_group")
@Configuration
public class BeanConfig {
    @Bean
    @Qualifier
    public Bkhech bkhech1() {
        Bkhech r = new Bkhech();
        r.setName("bkhech1");
        r.setId(1);
        return r;
    }

    @Bean
    @Qualifier
    public Bkhech bkhech2() {
        Bkhech r = new Bkhech();
        r.setName("bkhech2");
        r.setId(2);
        return r;
    }

    @Bean
    @Qualifier
    public Bkhech bkhech3() {
        Bkhech r = new Bkhech();
        r.setName("bkhech3");
        r.setId(3);
        return r;
    }

    @Bean
    @Grp
    public Bkhech bkhech4() {
        Bkhech r = new Bkhech();
        r.setName("bkhech4");
        r.setId(4);
        return r;
    }

    @Bean
    @Grp
    public Bkhech bkhech5() {
        Bkhech r = new Bkhech();
        r.setName("bkhech5");
        r.setId(5);
        return r;
    }

}
