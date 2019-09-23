package com.bkhech.home.practice.javacore.core.system;

import java.util.Map;
import java.util.Properties;

/**
 * @author guowm
 * @date 2019/9/23
 * @description
 *
 * # 定义
 * - https://blog.csdn.net/DSLZTX/article/details/50188251
 *
 */
public class SystemGetenvGetPropertiesTest {

    public static void main(String[] args) {
        testGetenv();
    }

    public static void testGetenv() {
        Map<String, String> getenv = System.getenv();
        Properties properties = System.getProperties();
        System.out.println("getenv = " + getenv);
        System.out.println("properties = " + properties);
    }

}
