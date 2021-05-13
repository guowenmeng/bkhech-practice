1. Java 中 final、finally、finalize 的区别与用法
· final 用于声明属性，方法和类，分别表示属性不可变，方法不可覆盖，类不可继承。
即如果一个类被声明为 final，意味着它不能作为父类被继承，因此一个类不能同时被声明为 abstract 的，又被声明为 final 的。
变量或方法被声明为 final，可以保证它们在使用中不被修改。
被声明为 final 的变量必须在声明时给赋予初值，而在以后的引用中只能读取，不可修改。
被声明为 final 的方法也同样只能使用，不能重载。

· finally 是异常处理语句结构的一部分，总是执行，
常见的场景：释放一些资源，例如前面所说的 redis、db 等。
在异常处理时提供 finally 块来执行任何清除操作，即在执行 catch 后会执行 finally 代码块。

· finalize 是 Object 类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法，
可以覆盖此方法提供垃圾收集时的其他资源回收，例如关闭文件等。

2. 关于重写hashcode和equals方法
> https://blog.csdn.net/u012557538/article/details/89861552

3. 合 List、Map、Set 等底层设计，以及其使用场景与注意细节
ArrayList、LinkedList
> https://mp.weixin.qq.com/s?__biz=MzA5NTE1MjY0NQ==&mid=2648832203&idx=1&sn=d613329ba4b4747012afc53aba76a1eb&chksm=8856e7a1bf216eb77f310d4823f3f089ac547a1f5e2eb1fce600388a3711d834074845085a49&scene=21#wechat_redirect

List 是一个缓存数据的容器，是 JDK 为开发者提供的一种集合类型。

ArrayList 是基于数组实现，LinkedList 是基于链表实现。
而使用场景：在新增、删除操作时，LinkedList 的性能要高于 ArrayList，而在查询、遍历操作的时候，ArrayList 的性能要高于 LinkedList。
这个答案是否准确呢？
首先，大家都知道 ArrayList、LinkedList 都继承了 AbstractList 抽象类，而 AbstractList 实现了 List 接口。ArrayList 使用数组实现，而 LinkedList 使用了双向链表实现。
在源码中，我们知道 ArrayList 除了实现克隆和序列化，还实现了 RandomAccess 接口。大家可能会对这个接口比较陌生，通过代码我们可以发现，这个接口其实是一个空接口，没有实现逻辑，那么 ArrayList 为什么要实现它呢？原来 RandomAccess 接口是一个标志接口，它标志着只要实现该接口，就能实现快速随机访问。
常错点：
在一次 ArrayList 删除操作的过程中，有下面两种写法：
第一种：
```java
public static void removeA(ArrayList<String> l) {
  for (String s : l){
      if (s.equals("aaa")) {
        l.remove(s);
      }
  }
}
```
第二种：
```java
public static void removeB(ArrayList<String> l) {
    Iterator<String> it = l.iterator();
    while (it.hasNext()) {
        String str = it.next();
        if (str.equals("aaa")) {
            it.remove();
        }
    }
}
```
第一种写法错误，第二种是正确的，原因是上面的两种写法都有用到 list 内部迭代器Iterator，即遍历时，ArrayList 内部创建了一个内部迭代器 iterator，在使用 next 方法来取下一个元素时，
会使用 ArrayList 里保存的一个用来记录 list 修改次数的变量 modCount，与 iterator 保存了一个叫 expectedModCount 的表示期望的修改次数进行比较，如果不相等则会抛出一个叫 ConcurrentModificationException 的异常。
且在 for 循环中调用 list 中的 remove 方法，会走到一个 fastRemove 方法，该方法不是 iterator 中的方法，而是 ArrayList 中的方法，在该方法只做了 modCount++，而没有同步到 expectedModCount。所以不一致就抛出了 ConcurrentModificationException 异常了。

如果有看过阿里 Java 编程规范就知道，在集合中进行 remove 操作时，不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素使用 Iterator 方式，如果并发操作，需要对 Iterator 对象加锁。

4. ArrayList的ensureCapacity()方法
> https://www.jianshu.com/p/17a447698e03

ArrayList 添加大量元素之前最好先使用ensureCapacity 方法，以减少增量重新分配的次数(减少扩容)