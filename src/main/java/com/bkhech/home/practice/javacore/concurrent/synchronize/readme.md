# 1.java如何终止正在等待synchronized锁的线程？
没有办法终止，这也是 synchronized 这种内部锁机制为人所诟病的一点。
如果想要用可中断的锁，可以考虑用外部锁，java.util.concurrent.Lock，用Lock.lockInterruptibly()

# 2.synchronized优缺点？
synchronized 的缺点如下： 
- 不能响应中断； 
- 同一时刻不管是读还是写都只能有一个线程对共享资源操作，其他线程只能等待；
- 锁的释放由虚拟机来完成，不用人工干预，不过此既是缺点也是优点；
  优点是不用担心会造成死锁，缺点是由可能获取到锁的线程阻塞之后其他线程会一直等待，性能不高。

```
而lock接口的提出就是为了完善synchronized的不完美的，
首先lock是基于jdk层面实现的接口，synchronized是jvm层面的实现（由jvm控制）；
其次对于lock对象中的多个方法的调用，可以灵活控制对共享资源变量的操作，不管是读操作还是写操作
```

#