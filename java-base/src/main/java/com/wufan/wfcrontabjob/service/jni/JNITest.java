package com.wufan.wfcrontabjob.service.jni;

public class JNITest {

    public static void main(String[] args) {
        //输出 java.library.path 具体路径
        System.out.println("DLL path: " + System.getProperty("java.library.path"));
        //调用动态链接库中的具体方法
        AntiCrackingCfgJni.convertContent(2,
                "D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\libs\\native\\skill_table.json",
                "D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\libs\\native\\skill_table2.json"
        );

        AntiCrackingCfgJni.convertContent(1,
                "D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\libs\\native\\dino.ini",
                "D:\\IdeaProjects\\myproject\\bkhech-practice\\java-base\\libs\\native\\dino2.ini"
        );
    }

}
