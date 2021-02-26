1. Spring Boot的自动配置原理
> https://blog.csdn.net/u014745069/article/details/83820511

 Spring Boot启动的时候会通过@EnableAutoConfiguration注解找到META-INF/spring.factories配置文件中的所有自动配置类，
并对其进行加载，而这些自动配置类都是以AutoConfiguration结尾来命名的，它实际上就是一个JavaConfig形式的Spring容器配置类，
它能通过以Properties结尾命名的类中取得在全局配置文件中配置的属性 如：server.port，
而XxxProperties类是通过@ConfigurationProperties注解与全局配置文件中对应的属性进行绑定的。
如下图流程：
<img src="img\how-spring-boot-autoconfigure-works.png" style="zoom:90%;" />