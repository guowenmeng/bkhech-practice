package com.bkhech.home.practice.javacore.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestJavaNio {
    final static String in = "E:\\my.log";
    final static String out = "E:\\my_write.log";

    public static void main(String[] args) {
//        readAndWriteNio();
        readAndWriteNio2();
    }

    /**
     * 方式一 flip() clear()
     */
    public static void readAndWriteNio() {
        try (FileInputStream fin = new FileInputStream(new File(in));
             FileOutputStream fos = new FileOutputStream(new File(out));
             FileChannel channel = fin.getChannel();
             FileChannel outchannel = fos.getChannel()
        ) {
            // 字节
            int capacity = 10000;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            System.out.println("limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());

            int length;
            while ((length = channel.read(byteBuffer)) != -1) {
                System.out.println(">>> read before limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());
                //将当前位置置为limit，然后设置当前位置为0，也就是从0到limit这块，都写入到通道中
                byteBuffer.flip();
                System.out.println(">>> read after limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());

                int opulent;

                while ((opulent = outchannel.write(byteBuffer)) != 0) {
                    System.out.println(">>> write before limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());
                    System.out.println("读：" + length + "，写：" + opulent);
                }
                //将当前位置置为0，然后设置limit为容量，也就是从0到limit（容量）这块，都可以利用，通道读取的数据存储到0到limit这块
                byteBuffer.clear();
                System.out.println(">>> write after limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方式二  rewind() clear()
     */
    public static void readAndWriteNio2() {
        try (FileChannel channel = new RandomAccessFile(in, "r").getChannel();
             FileChannel outchannel = new RandomAccessFile(out, "rwd").getChannel()) {
            // 字节
            int capacity = 1000;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            System.out.println("limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());

            while (channel.read(byteBuffer) != -1) {
                System.out.println(">>> rewind before limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());
                //读取结束后的内容，相当于读取的长度
                int position = byteBuffer.position();
                //存储读取内容
                byte[] bt = new byte[position];
                //将position置为0，可以重读byteBuffer中的数据。如果不设置无法使用下面的get方法。
                byteBuffer.rewind();
                System.out.println(">>> rewind after limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());

                //将buffer中的数据 等价于重载方法：public ByteBuffer get(byte[] dst, int offset, int length)
                byteBuffer.get(bt);
                System.out.println(">>> write before limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());
                //将当前位置置为0，然后设置limit为容量，也就是从0到limit（容量）这块，都可以利用，通道读取的数据存储到0到limit这块
                byteBuffer.clear();
                System.out.println(">>> write after limit：" + byteBuffer.limit() + "，capacity：" + byteBuffer.capacity() + "，position：" + byteBuffer.position());

                System.out.println(">>> outchannel.size() = " + outchannel.size());
                /**
                 * 1.覆盖
                 * public abstract int write(ByteBuffer src) throws IOException;
                 * 2.追加
                 * public abstract int write(ByteBuffer src, long position) throws IOException;
                 * 但是需要结合 RandomAccessFile使用
                 * RandomAccessFile应用场景：
                 *   1、向10G文件末尾插入指定内容，或者向指定指针位置进行插入或者修改内容。
                 *   2、断点续传，使用seek()方法不断的更新下载资源的位置。
                 */
//                outchannel.write(ByteBuffer.wrap(bt));
                outchannel.write(ByteBuffer.wrap(bt), outchannel.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
