1. Spring Boot的自动配置原理
> https://blog.csdn.net/u014745069/article/details/83820511

 Spring Boot启动的时候会通过@EnableAutoConfiguration注解找到META-INF/spring.factories配置文件中的所有自动配置类，
并对其进行加载，而这些自动配置类都是以AutoConfiguration结尾来命名的，它实际上就是一个JavaConfig形式的Spring容器配置类，
它能通过以Properties结尾命名的类中取得在全局配置文件中配置的属性 如：server.port，
而XxxProperties类是通过@ConfigurationProperties注解与全局配置文件中对应的属性进行绑定的。
如下图流程：
<img src="img\how-spring-boot-autoconfigure-works.png" style="zoom:90%;" />

---
eg:
- springboot核心是自动装配
- spring ioc的升级过程
 1. xml方式
 2. 注解方式
 3. javaconfig方式
 4. springboot提供的自动装配
- springboot自动装配的核心
 1. EnableAutoConfiguration
 2. @Import(AutoConfigurationImportSelector.class)
 3. ImportSelector.selectImports
 4. META-INF/spring.factories
  ImportSelector.selectImports返回的就是javaconfig类，再根据Condition筛选后，决定是否执行注入
  官方提供的自动装配配置在spring-boot-autoconfigure中的META-INF/spring.factories中，如spring-boot-starter-data-redis
  非官方需要自己提供spring.factories，如mybatis-spring-boot-autoconfigure中定义了META-INF/spring.factories
- 通过继承ImportSelector.selectImports实现spring注入

```java
@SpringBootApplication
@EnableSelectImportService
public class SelectImportApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SelectImportApp.class);
        SelectImportService service = context.getBean(SelectImportService.class);
        service.hello();
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MySelectImport.class)
public @interface EnableSelectImportService {
}

public class MySelectImport implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{SelectImportService.class.getName()};
    }
}

public class SelectImportService {
    public void hello(){
        System.out.println("hello selectimport");
    }
}
```
---