package com.bkhech.home.practice.javacore.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author guowm
 * @date 2019/10/23
 * @description https://www.jianshu.com/p/ba15d066f777
 */
public class URLTest {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        // 创建对象
        URI uri = URI.create("http://localhost:8080/a.html");
        URL url1 = uri.toURL();
        String path = url1.getPath();
        System.out.println("path = " + path);
        URL url = new URL("http://localhost:8080/a.html");
        String path1 = url.toURI().getPath();
        System.out.println("path1 = " + path1);
    }
}
