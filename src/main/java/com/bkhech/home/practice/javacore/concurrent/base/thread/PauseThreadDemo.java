package com.bkhech.home.practice.javacore.concurrent.base.thread;

/**
 * 实现线程的暂停，继续样例
 * @author guowm
 * @date 2021/3/2
 */
public class PauseThreadDemo {

    public static void main(String[] args) {
        PauseThread pauseThread = new PauseThread();
        pauseThread.start();

        try {
            Thread.sleep(1000);
            pauseThread.pauseThread();

            Thread.sleep(5000);
            pauseThread.resumeThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 实现线程的暂停，继续
 *
 * @author guowm
 * @date 2021/3/2
 */
class PauseThread extends Thread {

    private final Object lock = new Object();

    private volatile boolean pause;

    /**
     * 调用该方法实现线程的暂停
     */
    void pauseThread() {
        pause = true;
    }

    /**
     * 调用该方法实现恢复线程的运行
     */
    void resumeThread() {
        pause = false;
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * 这个方法只能在run 方法中实现，不然会阻塞主线程
     */
    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        int index = 0;
        while (true) {
            while (pause) {
                onPause();
            }

            try {
                System.out.println(index);
                Thread.sleep(50);
                ++index;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

}
