package com.bkhech.home.practice.jni;

/**
 * Java-JNI调用过程
 * {@literal https://blog.csdn.net/FromZeroJiYuan/article/details/121528312}
 * JDK命令生成头文件：
 *  javac -encoding utf-8 -h ./ JNITest.java #-h指定生成头文件的目录
 * @author guowm
 * @date 2022/3/2
 */
public class JNITest {
    static {
        //从 java.library.path 路径上加载动态链接库, 启动时设置： -Djava.library.path=.\libs
        System.loadLibrary("MyFirstDll");
//        System.load("E:\\Program Files\\Microsoft\\source\\repos\\MyFirstDll\\x64\\Debug\\MyFirstDll.dll");
    }

    //定义native方法
    public static native void callCppMethod();

}
