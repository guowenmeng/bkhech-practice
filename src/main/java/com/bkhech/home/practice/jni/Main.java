package com.bkhech.home.practice.jni;

/**
 * JNI测试入口类
 * @author guowm
 * @date 2022/3/2
 */
public class Main {
    public static void main(String[] args) {
        //输出 java.library.path 具体路径
        System.out.println("DLL path: " + System.getProperty("java.library.path"));
        //调用动态链接库中的具体方法
        JNITest.callCppMethod();
    }
}
