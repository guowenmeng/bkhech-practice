package com.wufan.wfcrontabjob.service.jni;

/**
 * Java-JNI调用过程
 *
 * 注意：
 * 我的方法名字是 convertContent，包是com.wufan.wfcrontabjob.service.jni;，
 * 所以c程序里面的方法名必须是Java_com_wufan_wfcrontabjob_service_jni_AntiCrackingCfgJni_convertContent
 * 解释一下：java是固定的，_代表点，com_wufan_wfcrontabjob_service_jni对应包名，
 * AntiCrackingCfgJni 代表类的名字，convertContent 是方法名，
 * 如果java文件在其他的包里面，命名就要发生相应的变化，
 *
 * @author guowm
 * @date 2022/4/15
 */
public class AntiCrackingCfgJni {
    static {
        System.load("D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\libs\\native\\5fundealgamecfg.dll");
    }
    /**
     * 转化
     * @param type 1:金手指， 2：一键技能
     * @param sourceFile 源文件路径名
     * @param targetFile 目标文件路径名
     */
    public static native void convertContent(int type, String sourceFile, String targetFile);
}
