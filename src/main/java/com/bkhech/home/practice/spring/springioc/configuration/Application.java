package com.bkhech.home.practice.spring.springioc.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * https://www.cnblogs.com/Tony100/p/14423334.html
 * {@link org.springframework.context.annotation.Configuration} Full/Lite模式测试样例
 *
 * 当@Bean方法在没有使用@Configuration注释的类中声明时，它们被称为在Lite模式下处理。
 * 它包括：在@Component中声明的@Bean方法，甚至只是在一个非常普通的类中声明的Bean方法，都被认为是Lite版的配置类。@Bean方法是一种
 * 通用的工厂方法（factory-method）机制。和Full模式的@Configuration不同，Lite模式的@Bean方法不能声明Bean之间的依赖关系。因此，
 * 这样的@Bean方法不应该调用其他@Bean方法。每个这样的方法实际上只是一个特定Bean引用的工厂方法(factory-method)，没有任何特殊的运行时语义。
 *
 * 自Spring5.2（对应Spring Boot 2.2.0）开始，内置的几乎所有的@Configuration配置类都被修改
 * 为了@Configuration(proxyBeanMethods = false)，目的何为？
 * 答：以此来降低启动时间，为Cloud Native继续做准备。
 *
 * 优缺点
 * 优点：
 * 运行时不再需要给对应类生成CGLIB子类，提高了运行性能，降低了启动时间
 * 可以该配置类当作一个普通类使用喽：也就是说@Bean方法 可以是private、可以是final
 * 缺点：
 * 不能声明@Bean之间的依赖，也就是说不能通过方法调用来依赖其它Bean
 * （其实这个缺点还好，很容易用其它方式“弥补”，比如：把依赖Bean放进方法入参里即可）
 *
 * 结论：只要不标识@Configuration(proxyBeanMethods=true)其他都是lite模式
 * @author guowm
 * @date 2021/3/3
 *
 * @see org.springframework.context.annotation.Configuration
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context.getBean("liteConfig", LiteConfig.class));
        // 配置类情况
        System.out.println(context.getBean(LiteConfig.class));
        System.out.println(context.getBean(LiteConfig.InnerConfig.class));

        String[] beanNames = context.getBeanNamesForType(User.class);
        for (String beanName : beanNames) {
            User user = context.getBean(beanName, User.class);
            System.out.println("beanName:" + beanName);
            System.out.println(user.getClass());
            System.out.println(user);
            System.out.println("------------------------");
        }
    }
}
