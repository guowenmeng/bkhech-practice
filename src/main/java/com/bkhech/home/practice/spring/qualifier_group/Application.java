package com.bkhech.home.practice.spring.qualifier_group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author guowm
 * @date 2021/12/13
 */
public class Application {

    //        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    //        XmlBeanDefinitionReader xr = new XmlBeanDefinitionReader(ac);
    //        xr.loadBeanDefinitions("Beans.xml");
    //
    //        ac.register(Application.class);
    //        ac.refresh();
    //        Application application = ac.getBean(Application.class);
    @Autowired
    @Qualifier("bkhech1")
    private Bkhech bkhech;

    @Autowired
    @Qualifier("bkhech2")
    private Bkhech bkhech2;

    @Autowired
    @Qualifier
    private List<Bkhech> list;

    @Autowired
    @Grp
    private List<Bkhech> listGrp;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(BeanConfig.class);
        acac.register(Application.class);

        final Application application = acac.getBean(Application.class);
        System.out.println(application.bkhech.getName());
        System.out.println(application.bkhech2.getName());

        System.out.println(Arrays.toString(application.list.toArray()));
        System.out.println(Arrays.toString(application.listGrp.toArray()));

        acac.close();
    }
}
