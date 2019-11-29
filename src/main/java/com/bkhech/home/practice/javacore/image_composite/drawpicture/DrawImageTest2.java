package com.bkhech.home.practice.javacore.image_composite.drawpicture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DrawImageTest2 {

    public static void main(String[] args) throws IOException {
        int WIDTH = 100;
        int HEIGHT = 36;
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        //取得画笔
        Graphics2D graphics = bi.createGraphics();

        // 绘制背景色
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH, HEIGHT );
        //绘制背景色结束
        // 定义随机字符
        char[] ch = "1234567890abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ"
                .toCharArray();

        int len = ch.length;
        // 重新设置绘制字符的颜色
        graphics.setColor(getRandomColor(100, 160, random));
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(len);
            //设置字体
            graphics.setFont(new Font("粗体", Font.BOLD + Font.ITALIC, 26));
            //绘制字符串
            graphics.drawString(ch[index] + "", i * 15 + 9, 30);
            //绘制干扰线条
            graphics.drawLine(random.nextInt(50), random.nextInt(50), random
                    .nextInt(80), random.nextInt(80));


        }
        graphics.dispose();
        // 写出图片
        FileOutputStream fos = new FileOutputStream("D:\\image_composite\\image.jpg");
        ImageIO.write(bi, "JPG", fos);
        fos.flush();
        if(fos != null) {
            fos.close();
        }
        System.out.println("success end");
    }
    /*****
     *
     * @param littleColorNum 红绿蓝每种颜色RGB最小值
     * @param largeColorNum 红绿蓝每种颜色RGB最大值
     * @param random 随机数对象
     * @return Color对象
     */
    public static Color getRandomColor(int littleColorNum, int largeColorNum, Random random) {
        if (littleColorNum > 255) {
            littleColorNum = 255;
        }
        if (largeColorNum > 255) {
            largeColorNum = 255;
        }
        int red = littleColorNum + random.nextInt(largeColorNum - littleColorNum);
        int green = littleColorNum + random.nextInt(largeColorNum - littleColorNum);
        int blue = littleColorNum + random.nextInt(largeColorNum - littleColorNum);
        return new Color(red, green, blue);
    }
}
