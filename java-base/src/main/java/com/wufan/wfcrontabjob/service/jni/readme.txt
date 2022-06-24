1.JDK命令生成头文件：
    javac -encoding utf-8 -h ./ AntiCrackingCfgJni.java
2.根据头文件，编写cpp文件(源码文件 jni_source.zip)
3.生成so或者dll文件
  注意：因为c++不是跨平台，所以最好是哪个平台运行哪个平台编译
    1) Linux下：
     gcc -fPIC -I /usr/lib/jvm/java-openjdk/include -I /usr/lib/jvm/java-openjdk/include/linux -shared -o lib5fundealgamecfg.so 5fundealgamecfg.cpp
     或者
     g++ -fPIC -I /usr/lib/jvm/java-openjdk/include -I /usr/lib/jvm/java-openjdk/include/linux -shared -o lib5fundealgamecfg.so 5fundealgamecfg.cpp
     注意：g++用来解决 undefined symbol: _ZNSt8ios_base4InitD1 库联接问题，
     其实链接可以用g++或者gcc -lstdc++，二者作用相等
     (
     参考：https://blog.csdn.net/yangxueyangxue/article/details/95586868，
     https://www.cnblogs.com/mingzhang/p/8084432.html
     )
    2) window下：
     可下载vscode编辑器或者使用MinGW (cygwin和MinGW都是gcc在windows下的编译环境)

