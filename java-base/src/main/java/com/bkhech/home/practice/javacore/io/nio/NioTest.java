package com.bkhech.home.practice.javacore.io.nio;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guowm
 * @date 2019/10/23
 * @description
 * https://blog.csdn.net/v123411739/article/details/50620289
 */
public class NioTest {

    public static void main(String[] args) throws Exception {

        //一次读取的字节长度
        int bufSize = 1000;
        //读取的文件
        File fin = new File("E:\\my.log");
        //写出的文件
        File fout = new File("E:\\my_write.log");
        long startTime = System.currentTimeMillis();
        FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

        FileChannel fcout = new RandomAccessFile(fout, "rws").getChannel();

        readFileByLine(fcin, rBuffer, fcout);
        long endTime = System.currentTimeMillis();

        System.out.printf("测试执行时间%s%n", (endTime - startTime));
        if (fcin.isOpen()) {
            fcin.close();
        }
        if (fcout.isOpen()) {
            fcout.close();
        }
    }

    public static void readFileByLine(FileChannel fcin, ByteBuffer rBuffer, FileChannel fcout) {
        String enter = "\n";
        //存储读取的每行数据
        List<String> dataList = new ArrayList<>();
        byte[] lineByte;

		String encode = "UTF-8";
        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];

            //fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
            while (fcin.read(rBuffer) != -1) {
                //读取结束后的位置，相当于读取的长度
                int rSize = rBuffer.position();
                //用来存放读取的内容的数组
                byte[] bs = new byte[rSize];
                //将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                rBuffer.rewind();
                //相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
                rBuffer.get(bs);
                rBuffer.clear();

                int startNum = 0;
                //换行符
                int LF = 10;
                //回车符
                int CR = 13;
                //是否有换行符
                boolean hasLF = false;
                for (int i = 0; i < rSize; i++) {
                    if (bs[i] == LF) {
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        //数组大小已经去掉换行符
                        lineByte = new byte[tempNum + lineNum];

                        //填充了lineByte[0]~lineByte[tempNum-1]
                        System.arraycopy(temp, 0, lineByte, 0, tempNum);
                        temp = new byte[0];
                        //填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]
                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);

                        //一行完整的字符串(过滤了换行和回车)
                        String line = new String(lineByte, 0, lineByte.length, encode);
                        dataList.add(line);
                        System.out.println("line = " + line);
                        writeFileByLine(fcout, line + enter);

                        //过滤回车符和换行符
                        if (i + 1 < rSize && bs[i + 1] == CR) {
                            startNum = i + 2;
                        } else {
                            startNum = i + 1;
                        }

                    }
                }
                if (hasLF) {
                    temp = new byte[bs.length - startNum];
                    System.arraycopy(bs, startNum, temp, 0, temp.length);
                } else {//兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            //兼容文件最后一行没有换行的情况
            if (temp != null && temp.length > 0) {
                String line = new String(temp, 0, temp.length, encode);
                dataList.add(line);
                System.out.println(line);
                writeFileByLine(fcout, line + enter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写到文件上
     * @param fcout
     * @param line
     */
    @SuppressWarnings("static-access")
    public static void writeFileByLine(FileChannel fcout, String line) {
        try {
            fcout.write(ByteBuffer.wrap(line.getBytes(StandardCharsets.UTF_8)), fcout.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
