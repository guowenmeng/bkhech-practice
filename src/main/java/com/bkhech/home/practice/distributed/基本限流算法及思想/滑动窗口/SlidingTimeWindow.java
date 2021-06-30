package com.bkhech.home.practice.distributed.基本限流算法及思想.滑动窗口;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 滑动窗口
 * @author guowm
 * @date 2021/6/28
 */
public class SlidingTimeWindow {
    //一个时间窗口，单位是毫秒
    private long timeWindow;
    //一个时间窗口分割多少块
    private int slot;
    //一个时间窗口限制的次数(在一个完整窗口期内允许通过的最大阈值)
    private long threshold;
    //最小单元格的时间长度，单位是毫秒
    //每个slot的时间段
    private long slotTime;
    //记录窗口滑动到的Node
    private Node lastNode;

    private Node rootNode;

    @Data
    static class Node {
        //起始时间
        private long time;
        //调用计数器
        private long counter;
        //指向下一个Node的变量，把所有的Node组成一个环
        private Node next;
        //身份id
        private int id;

        public Node() {
        }

        public Node(long time, long counter, int id) {
            this.time = time;
            this.counter = counter;
            this.id = id;
        }
    }

    /**
     *
     * @param timeWindowSecond 一个时间窗口，单位是秒
     * @param slot 一个时间窗口分割多少块
     * @param threshold 一个时间窗口限制的最大次数
     */
    public SlidingTimeWindow(int timeWindowSecond, int slot, long threshold) {
        this.slot = slot;
        this.threshold = threshold;
        this.timeWindow = TimeUnit.SECONDS.toMillis(timeWindowSecond);
        init();
    }

    private void init() {
        long current = System.currentTimeMillis();
        for (int i = 0; i < slot; i++) {
            if (lastNode == null) {
                lastNode = new Node(current, 0, i + 1);
                rootNode = lastNode;
            } else {
                lastNode.next = new Node(current, 0, i + 1);
                lastNode = lastNode.next;
            }
        }
        //组成环
//        lastNode.next = root;
        //每个slot的时间段
        slotTime = timeWindow / slot;
    }

    /**
     * 检查是否超限，如果未超限，次数加1
     * @return
     */
    public synchronized boolean checkAndAdd() {
        reset();
        long sum = getSum();
        System.out.println(sum);
        if (sum > threshold) {
            return false;
        }
        lastNode.setCounter(lastNode.getCounter() + 1);
        return true;
    }

    /**
     * 获取当前窗口的总数
     * @return
     */
    private long getSum() {
        long sum = 0L;
        Node currentNode = lastNode;
        for (int i = 0; i < slot; i++) {
            sum += currentNode.counter;
            currentNode = getNextNode(currentNode);
        }
        return sum;
    }

    /**
     * 滑动窗口
     */
    private void reset() {
        final long currentTimeMillis = System.currentTimeMillis();
        long time = lastNode.getTime();
        int count = (int) ((currentTimeMillis - time) / slotTime);
        if (count > slot) {
            count = slot;
        }
        reset(count, currentTimeMillis);
    }

    /**
     * 滑动窗口
     * @param num
     * @param currentTimeMillis
     */
    private void reset(int num, long currentTimeMillis) {
        if (num <= 0) {
            return;
        }
        Node currentNode = lastNode;
        for (int i = 0; i < num; i++) {
            currentNode = getNextNode(currentNode);
            currentNode.setTime(currentTimeMillis);
            currentNode.setCounter(0L);
        }
        lastNode = currentNode;
    }


    /**
     * 获取下一个节点（自动组成环）
     * @param currentNode
     * @return
     */
    private Node getNextNode(Node currentNode) {
        currentNode = currentNode.next;
        //组成环
        if (currentNode == null) {
            currentNode = rootNode;
        }
        return currentNode;
    }

    public static void main(String[] args) {
        final SlidingTimeWindow slidingTimeWindow = new SlidingTimeWindow(10, 10, 5);

        final ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                String ret;
                if (slidingTimeWindow.checkAndAdd()) {
                    ret = "SUCCESS";
                } else {
                    ret = "FAILED";
                }
                System.out.println(ret);
            });

            if (i%50 == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
