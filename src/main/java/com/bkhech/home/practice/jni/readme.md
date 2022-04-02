1. Java native本地方法定义
   > 例如： JNITest.java
2. JDK命令生成头文件
   javac -encoding utf-8 -h ./ JNITest.java #-h指定生成头文件的目录
3. 使用 Visual Studio中开发Jni dll库
   1） 打开 VS, 创建新项目，模板选择 动态链接库（DLL）,项目名称本例中设置位 MyFirstDll
   2） 将生成的文件头 copy
   1） 创建具体CPP文件
      > 引用头文件并实现其中的函数，也就是native方法将要实际执行的逻辑，CPP文件名随意，本例中为callCppMethod.cpp
   2) 将CPP文件编译为动态链接库
      选中当前项目，右键点击生成，成功后会生成项目名称对应的dll文件，本例中为 MyFirstDll.dll
      > 参考：https://blog.csdn.net/l460133921/article/details/73824985
      > https://blog.csdn.net/dmw412724/article/details/81477546
      > https://blog.csdn.net/FromZeroJiYuan/article/details/121528312
      > https://blog.csdn.net/w1992wishes/article/details/80283403
4. 运行Main进行测试
> 注意设置library路径 -Djava.library.path=./libs