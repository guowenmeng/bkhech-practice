package com.bkhech.home.practice.designpattern.proxy.cglib;

import com.bkhech.home.practice.designpattern.proxy.ProxyPerson;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author guowm
 * @date 2020/12/15
 * @description https://www.cnblogs.com/wyq1995/p/10945034.html
 */
public class CglibTest {

    public static void main(String[] args) {
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\src\\main\\java\\com\\bkhech\\home\\practice\\designpattern\\proxy");
        //创建Enhancer对象，类似于JDK动态代理的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(ProxyPerson.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的create方法就是正式创建代理类
        ProxyPerson proxyPerson = (ProxyPerson) enhancer.create();
        //调用代理类的study方法
        proxyPerson.study();
        proxyPerson.speak();
    }

}
