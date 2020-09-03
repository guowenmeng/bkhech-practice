package com.bkhech.home.practice.javacore.core.classload;

/**
 * @author guowm
 * @date 2020/7/30
 * @description
 */
public class ClassLoadTest {

    public static void main(String[] args) {

        try {
            //获得ClassLoaderTest这个类的类加载器
            ClassLoader loader = ClassLoadTest.class.getClassLoader();
            while(loader != null) {
                System.out.println(loader);
                //获得父加载器的引用
                loader = loader.getParent();
            }
            System.out.println(loader);

            String rootUrl = "C:/Users/guowm/Downloads";
            NetworkClassLoader networkClassLoader = new NetworkClassLoader(rootUrl);
            String classname = "com.bkhech.home.practice.javacore.core.classload.TestCL";
            Class clazz = networkClassLoader.loadClass(classname);
            //打印类加载器
            System.out.println(clazz.getClassLoader());

            Object newInstance = clazz.newInstance();
            //调用方法
            Object getStr = clazz.getMethod("getStr").invoke(newInstance);

            System.out.println("getStr = " + getStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
