## 注解Annotation
- 参考：https://www.jianshu.com/p/d55674caf6cf


###设置输出代理类
将JDK动态代理生成的class文件保存到本地，网上搜到的办法是加入：
```
System.getProperties().put(""sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
```
发现实际并未生效，先给答案，新版本JDK改为：
```
System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
```
如果不确定，可以在IDEA双击shift，输入ProxyGenerator，发现只有java.lang.reflect中存在该类，其中：
```java
private static final boolean saveGeneratedFiles = (Boolean)AccessController.doPrivileged(new GetBooleanAction("jdk.proxy.ProxyGenerator.saveGeneratedFiles"));
```
