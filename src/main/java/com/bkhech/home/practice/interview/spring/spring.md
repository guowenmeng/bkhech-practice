---

---

1  什么是Spring框架？Spring框架有哪些主要模块？
Spring框架是一个为Java应用程序开发提供综合、广泛的基础性支持的Java平台。Spring帮助开发者解决了开发中基础性的问题，使得开发人员可以专注于应用程序的开发。Spring框架本身也是按照设计模式精心打造，这使得我们可以在开发环境中安心地集成Spring框架，不必担心Spring是如何在后台工作的。
Spring大约18个基本模块，大致分为4类；分别是核心模块、AOP、数据访问、Web模块、测试模块。
核心模块包括：core、beans、context、context-support、expression共5个模块；
AOP模块包括：aop、aspects、instrument共3个模块；
数据访问模块包括：jdbc、tx、orm、oxm共4个模块；
Web模块包括：web、webmvc、websocket、webflux共4个模块；
集成测试模块：test模块。

2  使用Spring框架能带来哪些好处？
下面列举了一些使用Spring框架带来的主要好处：
（1）Dependency Injection(DI) 方法使得构造器和JavaBean properties文件中的依赖关系一目了然。
（2）与EJB容器相比较，IOC容器更加趋向于轻量级。这样一来IOC容器在有限的内存和CPU资源的情况下进行应用程序的开发和发布就变得十分有利。
（3）Spring并没有闭门造车，Spring利用了已有的技术，比如ORM框架、logging框架、J2EE、Quartz和JDK Timer，以及其他视图技术。
（4）Spring框架是按照模块的形式来组织的。由包和类的编号就可以看出其所属的模块，开发者只需选用他们需要的模块即可。
（5）要测试一项用Spring开发的应用程序十分简单，因为测试相关的环境代码都已经囊括在框架中了。更加简单的是，利用JavaBean形式的POJO类，可以很方便地利用依赖注入来写入测试数据。
（6）Spring的Web框架也是一个精心设计的Web MVC框架，为开发者们在Web框架的选择上提供了一个除主流框架比如Struts、过度设计的、不流行Web框架以外的选择。
（7）Spring提供了一个便捷的事务管理接口，适用于小型的本地事务处理（比如在单DB的环境下）和复杂的共同事务处理（比如利用JTA的复杂DB环境）。

3  什么是控制反转（IOC）？什么是依赖注入？
（1）控制反转是应用于软件工程领域的，在运行时被装配器对象用来绑定耦合对象的一种编程技巧，对象之间的耦合关系在编译时通常是未知的。在传统的编程方式中，业务逻辑的流程是由应用程序中早已被设定好关联关系的对象来决定的。在使用控制反转的情况下，业务逻辑的流程是由对象关系图来决定的，该对象关系图由装配器负责实例化，这种实现方式还可以将对象之间的关联关系的定义抽象化。绑定的过程是通过“依赖注入”实现的。
（2）控制反转是一种以给予应用程序中目标组件更多控制为目的设计范式，并在我们的实际工作中起到了有效的作用。
（3）依赖注入是在编译阶段尚未知所需的功能是来自哪个的类的情况下，将其他对象所依赖的功能对象实例化的模式。这就需要一种机制用来激活相应的组件以提供特定的功能，所以依赖注入是控制反转的基础。否则如果在组件不受框架控制的情况下，框架又怎么知道要创建哪个组件呢？

4  在Java中依赖注入有哪些方式？
（1）构造器注入。
（2）Setter方法注入(包含属性注入)。
（3）接口(回调)注入(例如： ...Aware接口的回调)。

5  BeanFactory和ApplicationContext有什么区别？
BeanFactory 可以理解为含有Bean集合的工厂类。BeanFactory 包含了bean的定义，以便在接收到客户端请求时将对应的Bean实例化。
BeanFactory还能在实例化对象时生成协作类之间的关系。此举将Bean自身从Bean客户端的配置中解放出来。BeanFactory还包含Bean生命周期的控制，调用客户端的初始化方法（initialization Methods）和销毁方法（destruction Methods）。
从表面上看，ApplicationContext如同Bean Factory一样具有Bean定义、Bean关联关系的设置，以及根据请求分发Bean的功能。但ApplicationContext在此基础上还提供了其他功能。 
（1）提供了支持国际化的文本消息。
（2）统一的资源文件读取方式。
（3）已在监听器中注册的Bean的事件。
以下是三种较常见的 ApplicationContext 实现方式。
（1）ClassPathXmlApplicationContext：从classpath的XML配置文件中读取上下文，并生成上下文定义。应用程序上下文从程序环境变量中取得。 
ApplicationContext context = new ClassPathXmlApplicationContext(“application.xml”); 
（2）FileSystemXmlApplicationContext ：由文件系统中的XML配置文件读取上下文。 
ApplicationContext context = new FileSystemXmlApplicationContext(“application.xml”); 
（3）XmlWebApplicationContext：由Web应用的XML文件读取上下文。

6  Spring提供几种配置方式来设置元数据？
将Spring配置到应用开发中有以下三种方式： 
（1）基于XML的配置。
（2）基于注解的配置。
（3）基于Java的配置。

7  如何使用XML配置的方式配置Spring？
在Spring框架中，依赖和服务需要专门的配置文件中实现，我一般用XML格式的配置文件。这些配置文件的格式采用公共的模板，由一系列的Bean定义和专门的应用配置选项组成。 
Spring XML配置的主要目的是使所有的Spring组件都可以用XML文件的形式来进行配置。这意味着不会出现其他的Spring配置类型（比如声明的方式或基于Java Class的配置方式）。
Spring的XML配置方式是使用被Spring命名空间所支持的一系列的XML标签来实现的。Spring有以下主要的命名空间：context、beans、jdbc、tx、aop、mvc和aso。
```xml
<beans>
   <!-- JSON Support -->
   <bean name="viewResolver"
        class="org.springframework.web.servlet.view.BeanNameViewResolver"/>
   <bean name="jsonTemplate"
        class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
   <bean id="restTemplate" class="org.springframework.web.client.RestTemplate"/>
</beans>
```

下面这个web.xml仅配置了DispatcherServlet，这最简单的配置便能满足应用程序配置运行时组件的需求。
```xml
<web-app>
   <display-name>Archetype Created Web Application</display-name>
   <servlet>
      <servlet-name>spring</servlet-name>
      <servlet-class>
         org.springframework.web.servlet.DispatcherServlet
      </servlet-class>
      <load-on-startup>1</load-on-startup>
   </servlet>
   <servlet-mapping>
      <servlet-name>spring</servlet-name>
      <url-pattern>/</url-pattern>
   </servlet-mapping>
</web-app>
```

8  Spring提供哪些配置形式？
Spring对Java配置的支持是由@Configuration注解和@Bean注解来实现的。由@Bean注解的方法将会实例化、配置和初始化一个新对象，这个对象将由Spring的IOC容器来管理。@Bean声明所起到的作用与元素类似。被@Configuration所注解的类则表示这个类的主要目的是作为Bean定义的资源。被@Configuration声明的类可以通过在同一个类的内部调用@bean方法来设置嵌入Bean的依赖关系。 
最简单的@Configuration 声明类请参考下面的代码：
```java
@Configuration
public class AppConfig{
   @Bean
   public MyService myService() {
      return new MyServiceImpl();
   }
}
```

与上面的@Bean配置文件相同的XML配置文件如下：
```xml
<beans>
   <bean id="myService" class="com.bkhech.services.MyServiceImpl"/>
</beans>
```
上述配置方式的实例化方式如下：
```java
public static void main(String[] args) {
   ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
   MyService myService = ctx.getBean(MyService.class);
   myService.doStuff();
}
```

要使用组件组建扫描，仅需用@ ComponentScan进行注解即可：
```java
@Configuration
@ComponentScan(basePackages = "com.bkhech")
public class AppConfig  {
}
```
在上面的例子中，com.bkhech包首先会被扫描到，然后在容器内查找被@Component声明的类，找到后将这些类按照Spring Bean定义进行注册。 
如果你要在Web应用开发中选用上述配置方式，需要用AnnotationConfigWebApplicationContext类来读取配置文件，可以用来配置Spring的Servlet监听器ContrextLoaderListener或者Spring MVC的DispatcherServlet。
```xml
<web-app>
   <context-param>
      <param-name>contextClass</param-name>
      <param-value>
         org.springframework.web.context.support.AnnotationConfigWebApplicationContext
      </param-value>
   </context-param>
   <context-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>com.bkhech.AppConfig</param-value>
   </context-param>
   <listener>
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   </listener>
   <servlet>
      <servlet-name>dispatcher</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
         <param-name>contextClass</param-name>
         <param-value>
            org.springframework.web.context.support.AnnotationConfigWebApplicationContext
         </param-value>
      </init-param>
      <init-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>com.bkhech.web.AppConfig</param-value>
      </init-param>
   </servlet>
   <servlet-mapping>
      <servlet-name>dispatcher</servlet-name>
      <url-pattern>/web/*</url-pattern>
   </servlet-mapping>
</web-app>
```

9  怎样用注解的方式配置Spring？
Spring在2.5版本以后开始支持用注解的方式来配置依赖注入。可以用注解的方式来替代XML方式的Bean描述，可以将Bean描述转移到组件类的内部，只需要在相关类上、方法上或者字段声明上使用注解即可。注解注入将会被容器在XML注入之前处理，所以后者会覆盖前者对于同一个属性的处理结果。
注解装配在Spring中是默认关闭的，需要在Spring文件中配置一下才能使用基于注解的装配模式。如果你想要在应用程序中使用注解的方式，请参考如下配置：
```xml
<beans>
   <context:annotation-config/>
</beans>
```

标签配置完成以后，就可以用注解的方式在Spring中向属性、方法和构造方法中自动装配变量。
下面是几种比较重要的注解类型。
（1）@Required：该注解应用于设值方法(as of 5.1, in favor of using constructor injection for required settings)。 
（2）@Autowired：该注解应用于有设值方法、非设值方法、构造方法和变量。 
（3）@Qualifier：该注解和@Autowired注解搭配使用，用于消除特定Bean自动装配的歧义。 
（4）JSR-250 Annotations：Spring支持基于JSR-250 注解的注解，@Resource、@PostConstruct 和 @PreDestroy。

10  请解释Spring Bean的生命周期？

<img src="img\spring-bean-lifecycle.png" style="zoom:90%;" />

Spring Bean的生命周期简单易懂。在一个Bean实例被初始化时，需要执行一系列初始化操作以达到可用的状态。同样，当一个Bean不再被调用时需要进行相关的析构操作，并从Bean容器中移除。 
Spring Bean Factory 负责管理在Spring容器中被创建的Bean的生命周期。Bean的生命周期由两组回调方法组成。 
（1）初始化之后调用的回调方法。 
（2）销毁之前调用的回调方法。 
Spring框架提供了以下四种方式来管理Bean的生命周期事件：
（1）InitializingBean和DisposableBean回调接口。
（2）针对特殊行为的其他Aware接口。
（3）Bean配置文件中的Custom init()方法和destroy()方法。
（4）@PostConstruct和@PreDestroy注解方式。
使用customInit()和 customDestroy()方法管理Bean生命周期的代码样例如下：

```xml
<beans>
   <bean id="demoBean" class="com.gupaoedu.task.DemoBean"
        init-Method="customInit" destroy-Method="customDestroy">
   </bean>
</beans>
```
11  Spring Bean作用域的区别是什么？
Spring容器中的Bean可以分为5个范围。所有范围的名称都是自说明的，但是为了避免混淆，还是让我们来解释一下。
（1）singleton：这种Bean范围是默认的，这种范围确保不管接收到多少个请求，每个容器中只有一个Bean的实例，单例的模式由Bean Factory自身来维护。 
（2）prototype：原形范围与单例范围相反，为每一个Bean请求提供一个实例。 
（3）request：在请求Bean范围内为每一个来自客户端的网络请求创建一个实例，在请求完成以后，Bean会失效并被垃圾回收器回收。 
（4）Session：与请求范围类似，确保每个Session中有一个Bean的实例，在Session过期后，Bean会随之失效。
（5）global-session：global-session和Portlet应用相关。当你的应用部署在Portlet容器中时，它包含很多portlet。如果你想要声明让所有的portlet共用全局的存储变量，那么这个全局变量需要存储在global-session中。全局作用域与Servlet中的Session作用域效果相同。
12  什么是Spring Inner Bean？
在Spring框架中，无论何时Bean被使用时，当仅被调用了一个属性，一个明智的做法是将这个Bean声明为内部Bean。内部Bean可以用setter注入“属性”和用构造方法注入“构造参数”的方式来实现。
比如，在我们的应用程序中，一个Customer类引用了一个Person类，我们要做的是创建一个Person的实例，然后在Customer内部使用。
```java
public class Customer{
   private Person person;
}
public class Person{
   private String name;
   private String address;
   private int age;
}
```

内部Bean的声明方式如下：
```xml
<bean id="CustomerBean" class="com.gupaoedu.common.Customer">
   <property name="person">
      <bean class="com.gupaoedu.common.Person">
         <property name="name" value="lokesh" />
         <property name="address" value="India" />
         <property name="age" value="34" />
      </bean>
   </property>
</bean>
```
13  Spring框架中的单例Bean是线程安全的吗？
Spring框架并没有对单例Bean进行任何多线程的封装处理。关于单例Bean的线程安全和并发问题需要开发者自行搞定。但实际上，大部分Spring Bean并没有可变的状态（比如Serview类和DAO类），所以在某种程度上说，Spring的单例Bean是线程安全的。如果你的Bean有多种状态（比如View Model对象），就需要自行保证线程安全。
最浅显的解决办法就是将多态Bean的作用域由“singleton”变更为“prototype”。
14  请举例说明如何在Spring中注入一个Java 集合？
Spring提供了以下四种集合类的配置元素：
（1）<list>标签用来装配可重复的list值。 
（2）<set>标签用来装配没有重复的set值。 
（3）<map>标签可用来注入键和值，可以为任何类型的键值对。 
（4）<props>标签支持注入键和值都是字符串类型的键值对。 
下面看一下具体的例子：
```xml
<beans>
   <bean id="javaCollection" class="com.gupaoedu.JavaCollection">
      <property name="customList">
         <list>
            <value>INDIA</value>
            <value>Pakistan</value>
            <value>USA</value>
            <value>UK</value>
         </list>
      </property>
      <property name="customSet">
         <set>
            <value>INDIA</value>
            <value>Pakistan</value>
            <value>USA</value>
            <value>UK</value>
         </set>
      </property>
      <property name="customMap">
         <map>
            <entry key="1" value="INDIA"/>
            <entry key="2" value="Pakistan"/>
            <entry key="3" value="USA"/>
            <entry key="4" value="UK"/>
         </map>
      </property>
      <property name="customProperies">
         <props>
            <prop key="admin">admin@gupaoedu.com</prop>
            <prop key="support">support@gupaoedu.com</prop>
         </props>
      </property>
   </bean>
</beans>
```
15  如何向Spring Bean中注入java.util.Properties？
第一种方法是使用如下面代码所示的标签：
```xml
<bean id="adminUser" class="com.gupaoedu.common.Customer">
   <property name="emails">
      <props>
         <prop key="admin">admin@gupaoedu.com</prop>
         <prop key="support">support@gupaoedu.com</prop>
      </props>
   </property>
</bean>
```
也可用“util:”命名空间来从properties文件中创建一个propertiesbean，然后利用setter方法注入Bean的引用。

16  请解释Spring Bean的自动装配？
在Spring框架中，在配置文件中设定Bean的依赖关系是一个很好的机制，Spring容器还可以自动装配合作关系Bean之间的关联关系。这意味着Spring可以通过向Bean Factory中注入的方式自动搞定Bean之间的依赖关系。自动装配可以设置在每个Bean上，也可以设定在特定的Bean上。
下面的XML配置文件表明了如何根据名称将一个Bean设置为自动装配：
```xml
<bean id="employeeDAO" class="com.gupaoedu.EmployeeDAOImpl" autowire="byName" />
```

除了Bean配置文件中提供的自动装配模式，还可以使用@Autowired注解来自动装配指定的Bean。在使用@Autowired注解之前需要按照如下的配置方式在Spring配置文件中进行配置。
```xml
<context:annotation-config />
```

也可以通过在配置文件中配置AutowiredAnnotationBeanPostProcessor 达到相同的效果。
```xml
<bean class ="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor"/>

```
配置好以后就可以使用@Autowired来标注了。
```java
@Autowired
public EmployeeDAOImpl ( EmployeeManager manager ) {
      this.manager = manager;
}
```
17  自动装配有哪些局限性？
自动装配有如下局限性。
	重写：你仍然需要使用和< property>设置指明依赖，这意味着总要重写自动装配。
	原生数据类型：你不能自动装配简单的属性，如原生类型、字符串和类。
	模糊特性：自动装配总是没有自定义装配精确，因此如果可能尽量使用自定义装配。

18  请解释各种自动装配模式的区别？
在Spring框架中共有5种自动装配，让我们逐一分析。 
（1）no：这是Spring框架的默认设置，在该设置下自动装配是关闭的，开发者需要自行在Bean定义中用标签明确地设置依赖关系。
（2）byName：该选项可以根据Bean名称设置依赖关系。当向一个Bean中自动装配一个属性时，容器将根据Bean的名称自动在配置文件中查询一个匹配的Bean。如果找到就装配这个属性，如果没找到就报错。
（3）byType：该选项可以根据Bean类型设置依赖关系。当向一个Bean中自动装配一个属性时，容器将根据Bean的类型自动在配置文件中查询一个匹配的Bean。如果找到就装配这个属性，如果没找到就报错。
（4）constructor：它的自动装配和byType模式类似，但是仅适用于有与构造器相同参数的Bean，如果在容器中没有找到与构造器参数类型一致的Bean，那么将会抛出异常。
（5）autodetect：该模式自动探测使用构造器自动装配或者byType自动装配。首先会尝试找合适的带参数的构造器，如果找到就是用构造器自动装配，如果在Bean内部没有找到相应的构造器或者是无参构造器，容器就会自动选择byTpe的自动装配方式。

19  请举例解释@Required Annotation？
在产品级别的应用中，IOC容器可能声明了数十万了Bean，Bean与Bean之间有着复杂的依赖关系。设置注解方法的短板之一就是验证所有的属性是否被注解是一项十分困难的操作。可以通过设置“dependency-check”来解决这个问题。
在应用程序的生命周期中，你可能不大愿意花时间验证所有Bean的属性是否按照上下文文件正确配置。或者你宁可验证某个Bean的特定属性是否被正确设置。即使用“dependency-check”属性也不能很好地解决这个问题，在这种情况下你需要使用@Required 注解。 
需要用如下的方式使用来标明Bean的设值方法。
```java
public class EmployeeFactoryBean extends AbstractFactoryBean<Object> {
   private String designation;
   public String getDesignation() {
      return designation;
   }
   @Required
   public void setDesignation(String designation) {
      this.designation = designation;
   }
}
```

RequiredAnnotationBeanPostProcessor是Spring中的后置处理器，用来验证被@Required 注解的Bean属性是否被正确设置了。在使用RequiredAnnotationBeanPostProcessor验证Bean属性之前，要在IOC容器中对其进行注册：
```xml
<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />
```
但是如果没有属性被用@Required注解过，后置处理器会抛出一个BeanInitializationException异常。

20  请举例说明@Qualifier注解？
(1) @Qualifier注解意味着可以在被标注Bean的字段上自动装配。
(2) @Qualifier注解可以根据bean名称来消除Spring不能准确识别的多个同类型的Bean注入。
(3) 在依赖注入或者依赖查找时用来分组



