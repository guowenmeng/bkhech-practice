package com.bkhech.home.practice.javacore.collection.custom_queue;

/*
1.定义接口时，一定要写注释，接口的注释，方法的注释等等，这样别人看我们的接口时，会轻松很多‘；
2.定义接口时，要求命名简洁明了，最好让别人一看见命名就知道这个接口是干啥的，比如我们命名为 Queue，别人一看就清楚这个接口是和队列相关的；
3.用好泛型，因为我们不清楚放进队列中的到底都是那些值，所以我们使用了泛型 T，表示可以在队列中放任何值；
4.我们在接口中定义了基础的元素 Node，这样队列子类如果想用的话，可以直接使用，增加了复用的可能性。
 */
/**
 * 定义队列接口。定义泛型，可以让使用者放任意类型到队列中
 * @author guowm
 * @date 2021/8/26
 */
public interface MyQueue<T> {

    /**
     * 放数据
     * @param item 队列元素
     * @return true 成功，false 失败
     */
    boolean put(T item);

    /**
     * 拿数据
     * @return 一个队列元素， 泛型值
     */
    T take();

    /**
     * 队列中元素的基本结构
     * @author guowm
     * @date 2021/8/26
     */
    class Node<T> {
        // 数据本身
        T item;
        // 下一个元素
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }
}
