## 图片合成

### 生成二维码 jar

> [生成二维码之 Java ](https://www.jianshu.com/p/05e9ee773898)
>
> [二维码的生成和解析](https://www.cnblogs.com/nihaorz/p/6831516.html)

```xml
      <dependency>
			<groupId>com.google.zxing</groupId>
			<artifactId>core</artifactId>
			<version>3.3.3</version>
		</dependency>

        <dependency>
			<groupId>com.google.zxing</groupId>
			<artifactId>javase</artifactId>
			<version>3.3.3</version>
		</dependency>
```

> 生成带logo的二维码可是用图片合成的方式去做

### 图片合成

> [基础教程](https://blog.csdn.net/rentian1/article/details/78009633)
>
> [Java创建图片并绘图](https://blog.csdn.net/zhouyingge1104/article/details/80995845)
>
> [JAVA 绘制验证码图像及解决黑色背景问题](https://blog.csdn.net/wjw521wjw521/article/details/75014097)
>
> [几张图片合成一张图片](https://blog.csdn.net/u011768325/article/details/37961907)

```java
BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
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
        int xPoints[] = new int[]{xPoly, xPoly + 20, xPoly + 260, xPoly + 260, xPoly + 20};
        int yPoints[] = new int[]{yPoly, yPoly - 35, yPoly - 35, yPoly + 35, yPoly + 35};
        int nPoints = 5;

//        g.drawPolyline(xPoints, yPoints, nPoints);

        /** 折线 （如果最后一个点和第一个点不重合，则最后一个点和第一个点自动连接） */
        g.drawPolygon(xPoints, yPoints, nPoints);

        /** 释放资源 */
        g.dispose();
        try {
            val = ImageIO.write(bi, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

