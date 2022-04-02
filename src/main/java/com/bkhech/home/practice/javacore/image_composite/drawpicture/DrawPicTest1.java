package com.bkhech.home.practice.javacore.image_composite.drawpicture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawPicTest1 {
    static final int WIDTH = 350;
    static final int HEIGHT = 600;

    public static void main(String[] args) {


        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

        final File file = new File("D:\\image_composite\\javaPic.png");

        try {
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }


        writeImage(bi, "png", file);
        System.out.println("绘图成功");

    }

    /** 通过指定参数写一个图片  */
    public static boolean writeImage(BufferedImage bi, String picType, File file) {

//        Graphics g = bi.getGraphics();
        Graphics2D g = bi.createGraphics();
        // 绘制背景色
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT );

        //线的颜色
        g.setColor(new Color(0,0,0));

        /** 线段 */
//        g.drawLine(0, 100, 100, 100);

        /** 绘制一个椭圆  */
        int x = 10, y = 40, width = 40, height = 40;
        g.drawOval(x, y, width, height);

        /** 折线 （各个点相互连接） */
        int xPoly = 50;
        int yPoly = 85;
        int[] xPoints = new int[]{xPoly, xPoly + 20, xPoly + 260, xPoly + 260, xPoly + 20};
        int[] yPoints = new int[]{yPoly, yPoly - 35, yPoly - 35, yPoly + 35, yPoly + 35};
        int nPoints = 5;

//        g.drawPolyline(xPoints, yPoints, nPoints);

        /** 折线 （如果最后一个点和第一个点不重合，则最后一个点和第一个点自动连接） */
        g.drawPolygon(xPoints, yPoints, nPoints);

        /** 释放资源 */
        g.dispose();
        boolean val = false;
        try {
            val = ImageIO.write(bi, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }

}
