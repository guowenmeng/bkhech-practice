> https://www.cnblogs.com/54chensongxia/p/12187570.html

ABA问题怎么解：AtomicStampedReference和AtomicMarkableReference

并发编程的基石——CAS机制这篇文章中介绍到CAS机制有一个缺点就是，ABA问题：
CAS在操作的时候会检查变量的值是否被更改过，如果没有则更新值，
但是带来一个问题是：
就是说一个线程把数据A变为了B，然后又重新变成了A。此时另外一个线程读取的时候，发现A没有变化，就误以为是原来的那个A，
因此CAS机制会更新这个值。但是实际上这个值已经被修改过了。

AtomicStampedReference和AtomicMarkableReference就是用来解决CAS中的ABA问题的。他们解决ABA问题的原理类似，都是通过一个版本号来区分有没被更新过。

AtomicStampedReference：带版本戳的原子引用类型，版本戳为int类型。
AtomicMarkableReference：带版本戳的原子引用类型，版本戳为boolean类型。

1. AtomicStampedReference使用列子
```java
public class AtomicStampedReferenceDemo {

    private static final Long var = new Long(1);

    public static void main(String[] args) {
        AtomicStampedReference<Long> referenceDemo = new AtomicStampedReference(var, 1);
        System.out.println("now value:" + referenceDemo.getReference().intValue());
        int stamp = referenceDemo.getStamp();
        System.out.println("now stamp:" + stamp);
        boolean b = referenceDemo.compareAndSet(var, new Long(2), stamp, stamp + 1);
        if (b) {
            System.out.println("success set value...");
            System.out.println("now value:" + referenceDemo.getReference().intValue());
            stamp = referenceDemo.getStamp();
            System.out.println("now stamp:" + stamp);
        } else {
            System.out.println("failed set value...");
            System.out.println("now value:" + referenceDemo.getReference().intValue());
            stamp = referenceDemo.getStamp();
            System.out.println("now stamp:" + stamp);
        }
    }

}
```
2. AtomicMarkableReference使用
关于AtomicMarkableReference的原理其实是与AtomicStampedReference类似的。

区别是AtomicMarkableReference的版本戳是boolean类型，所以导致版本状态只有两个：true或者false。

所以，我更倾向于称呼AtomicMarkableReference为带标记的原子引用类型。

版本戳 = true，表示此引用被标记。
版本戳 = false，表示此引用未被标记。