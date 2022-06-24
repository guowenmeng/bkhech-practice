1.问题：
执行 .\mvnw clean install -DskipTests报错，无法打包 
> [ERROR] com/bkhech/home/practice/javacore/core/property_descriptor/PropertyDescriptorUtil.java:[82,37]
无法访问com.sun.beans.introspect.PropertyInfo
找不到com.sun.beans.introspect.PropertyInfo的类文件

解决：暂时注释掉，运行的时候打开