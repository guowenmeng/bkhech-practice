package com.bkhech.home.practice.javacore.collection.custom_queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/*
put 方法的实现有几点我们需要注意的是：

1.注意 try catch finally 的节奏，catch 可以捕捉多种类型的异常，我们这里就捕捉了超时异常和未知异常，在 finally 里面一定记得要释放锁，不然锁不会自动释放的，这个一定不能用错，体现了我们代码的准确性；
2.必要的逻辑检查还是需要的，比如入参是否为空的空指针检查，队列是否满的临界检查，这些检查代码可以体现出我们逻辑的严密性；
3.在代码的关键地方加上日志和注释，这点也是非常重要的，我们不希望关键逻辑代码注释和日志都没有，不利于阅读代码和排查问题；
4.注意线程安全，此处实现我们除了加锁之外，对于容量的大小（size）我们选择线程安全的计数类：AtomicInteger，来保证了线程安全；
5.加锁的时候，我们最好不要使用永远阻塞的方法，我们一定要用带有超时时间的阻塞方法，此处我们设置的超时时间是 300 毫秒，也就是说如果 300 毫秒内还没有获得锁，put 方法直接返回 false，当然时间大小你可以根据情况进行设置；
6.根据不同的情况设置不同的返回值，put 方法返回的是 false，在发生异常时，我们可以选择返回 false，或者直接抛出异常；
7.put 数据时追加到队尾的，所以我们只需要把新数据转化成 DIYNode，放到队列的尾部即可。
 */

/**
 * 自定义队列
 * 基于链表数据结构的队列
 *
 * @author guowm
 * @date 2021/8/26
 */
@Slf4j
public class LinkedMyQueue<T> implements MyQueue<T> {

    /**
     * 队列头
     */
    private volatile Node<T> head;

    /**
     * 队列尾
     */
    private volatile Node<T> tail;

    /**
     * 自定义队列元素
     */
    class MyNode extends Node<T> {
        public MyNode(T item) {
            super(item);
        }
    }

    /**
     * 队列的大小
     */
    private final AtomicInteger size = new AtomicInteger(0);

    /**
     * 容量
     */
    private final Integer capacity;

    /**
     * 放数据锁
     */
    private final ReentrantLock putLock = new ReentrantLock();

    /**
     * 拿数据锁
     */
    private final ReentrantLock takeLock = new ReentrantLock();

    /**
     * 无参构造器，默认最大容量是 Integer.MAX_VALUE
     */
    public LinkedMyQueue() {
        this.capacity = Integer.MAX_VALUE;
        head = tail = new MyNode(null);
    }

    /**
     * 有参构造器，可以设定容量的大小
     *
     * @param capacity 队列容量
     */
    public LinkedMyQueue(Integer capacity) {
        // 进行边界校验
        if (capacity == null || capacity < 0) {
            throw new IllegalStateException();
        }
        this.capacity = capacity;
        head = tail = new MyNode(null);
    }


    /**
     * 放数据
     *
     * @param item 队列元素
     * @return true 成功，false 失败
     */
    @Override
    public boolean put(T item) {
        //禁止空数据
        if (null == item) {
            return false;
        }

        try {
            // 尝试加锁，300 毫秒未获取到锁直接被打断
            final boolean lockSuccess = putLock.tryLock(300, TimeUnit.MILLISECONDS);
            if (!lockSuccess) {
                throw new RuntimeException("加锁失败");
            }

            // 校验队列大小
            if (size.get() >= capacity) {
                log.info("queue is full");
                return false;
            }

            // 追加到队尾
            tail.next = new MyNode(item);
            tail = tail.next;

            // 计数，队列大小加一
            size.incrementAndGet();
            return true;
        } catch (InterruptedException e) {
            log.error("try lock 300 ms timeout.", e);
        } catch (Exception e) {
            log.error("put error.", e);
        } finally {
            putLock.unlock();
        }
        return false;
    }

    /**
     * 拿数据
     *
     * @return 一个队列元素， 泛型值
     */
    @Override
    public T take() {
        // 队列是空的
        if (size.get() == 0) {
            return null;
        }
        try {
            // 拿数据我们设置的超时时间更短
            final boolean lockSuccess = takeLock.tryLock(200, TimeUnit.MILLISECONDS);
            if (!lockSuccess) {
                throw new RuntimeException("加锁失败");
            }
            // 把头结点的下一个元素拿出来
            final Node<T> exceptHead = head.next;
            // 把头结点的值拿出来
            final T result = exceptHead.item;
            // 把头结点的值置为 null，帮助 gc
            exceptHead.item = null;
            // 重新设置头结点的值
            head = exceptHead;
            // 计数，队列大小减一
            size.decrementAndGet();
            // 返回头结点的值
            return result;
        } catch (InterruptedException e) {
            log.error("tryLock 200 ms timeout", e);
        } catch (Exception e) {
            log.error("take error.", e);
        } finally {
            takeLock.unlock();
        }
        return null;
    }
}
