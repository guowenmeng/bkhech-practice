package com.bkhech.home.practice.javacore.image_composite.drawpicture;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/11/27
 */
public class BaseTest {

    public static void main(String[] args) throws Exception {
//        qrcodeGen();
//        qrcodeLogoComposite();
        bgQrcodeLogoCompositeCustomGraphics();
    }

    /**
     * 背景、二维码和logo合成(自定义画布)
     * @throws Exception
     */
    public static void bgQrcodeLogoCompositeCustomGraphics() throws Exception {
        String qrCodeText = "http://www.baidu.com?shareId=2";
        String qrcodeFormat = MediaType.IMAGE_PNG.getSubtype();

        int WIDTH = 350;
        int HEIGHT = 600;

        // 绘制一个椭圆
        int ovalX = 9, ovalY = 40, ovalWidth = 40, ovalHeight = 40;

        //头像
        int avatarX = ovalX + 2;
        int avatarY = ovalY + 2;
        int avatarWidth = ovalWidth - 2;
        int avatarHeight = ovalHeight -2;

        //折线
        int polyX = ovalX + 40;
        int polyY = ovalY + 45;
        int[] xPoints = new int[]{polyX, polyX + 20, polyX + 260, polyX + 260, polyX + 20};
        int[] yPoints = new int[]{polyY, polyY - 35, polyY - 35, polyY + 35, polyY + 35};
        int nPoints = 5;

        //邀请语
        int inviteLanX = polyX;
        int inviteLanY = polyY;

        //标题
        int titleY = inviteLanY + 150;

        //二维码
        int qrWidth = WIDTH - 20;
        int qrHeight = qrWidth;
        int qrY = titleY + 13;

        //创建背景图像
        BufferedImage imageBg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphicsBg = imageBg.createGraphics();
        // 绘制背景色
        graphicsBg.setColor(Color.white);
        graphicsBg.fillRect(0, 0, WIDTH, HEIGHT );

        /** 绘制一个椭圆  */
        //线的颜色
        graphicsBg.setColor(new Color(0,0,0));
        graphicsBg.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);

        /** 头像 */
        try {
            String avatarUrl = "http://static-img.abab618.com/ab_avatar.png";
            BufferedImage avatarBufferedImage = getAvatar(avatarUrl);
            graphicsBg.drawImage(avatarBufferedImage, avatarX, avatarY, avatarWidth, avatarHeight, null);
        } catch (IOException e) {
            System.out.println("getAvatar error:" + e.getMessage());
        }

        Font font = null;
        try {
            String ttf = "D:\\image_composite\\微软正黑体\\msjh.ttf";
            InputStream inputStream = new FileInputStream(new File(ttf));
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            font = font.deriveFont(Font.PLAIN, 15);

        } catch (FontFormatException e) {
            font = new Font("微软雅黑", Font.BOLD, 30);
        }

        /** 折线 （如果最后一个点和第一个点不重合，则最后一个点和第一个点自动连接） */
        graphicsBg.drawPolygon(xPoints, yPoints, nPoints);

//        添加邀请语
        String inviteLanOneLine = "免费拿6 元红包，充值游戏时直接";
        String inviteLanTwoLine = "抵扣，好兄弟 有福同享，一起来嗨";
        font = font.deriveFont(Font.PLAIN, 15);
        FontMetrics fontMetricsInviteLan = graphicsBg.getFontMetrics(font);
        graphicsBg.setFont(font);
        graphicsBg.setColor(new Color(15, 7, 9));
        graphicsBg.drawString(inviteLanOneLine,  inviteLanX + 20, inviteLanY);
        graphicsBg.drawString(inviteLanTwoLine, inviteLanX + 20, inviteLanY + 15);
//        graphicsBg.drawString(inviteLanOneLine, (imageBg.getWidth() - fontMetricsInviteLan.stringWidth(inviteLanOneLine)) / 2, fontMetricsInviteLan.getHeight() + inviteLanTop);
//        graphicsBg.drawString(inviteLanTwoLine, (imageBg.getWidth() - fontMetricsInviteLan.stringWidth(inviteLanTwoLine)) / 2, fontMetricsInviteLan.getHeight() + inviteLanTop + 15);

//        添加标题
        String title = "长按识别 或 扫一扫，轻松领取6元红包";
        font = font.deriveFont(Font.BOLD, 17);
        FontMetrics fontMetricsTitle = graphicsBg.getFontMetrics(font);
        graphicsBg.setFont(font);
        graphicsBg.setColor(new Color(15, 7, 9));
        graphicsBg.drawString(title, (imageBg.getWidth() - fontMetricsTitle.stringWidth(title)) / 2, titleY);

        QrcodeHandler qrcodeHandler = new QrcodeHandler();
        try {
            BufferedImage qRCodeBufferedImage = qrcodeHandler.getQrCode(qrCodeText, qrWidth, qrHeight);

            //合成logo
            File logoFile = new File("D:\\image_composite\\logo.png");
            FileInputStream fileInputStream = new FileInputStream(logoFile);
            qrcodeHandler.genQRCodeLogo(qRCodeBufferedImage, fileInputStream);

            graphicsBg.drawImage(qRCodeBufferedImage, (imageBg.getWidth() - qrWidth) / 2, qrY, qrWidth, qrHeight, null);

            graphicsBg.dispose();

            String qrcodePath = "D:\\image_composite\\ab." + qrcodeFormat;
            File qrcodeFile = new File(qrcodePath);
            ImageIO.write(imageBg, qrcodeFormat, qrcodeFile);

            String absolutePath = qrcodeFile.getAbsolutePath();

            System.out.println(absolutePath);

        } catch (WriterException e) {
            System.out.println("getQrCode error:" + e.getMessage());
        }
    }

    private static BufferedImage getAvatar(String url) throws IOException {

        BufferedImage imageAvatar = ImageIO.read(new URL(url));
        BufferedImage bufferedImageAvatar = new BufferedImage(imageAvatar.getWidth(), imageAvatar.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, imageAvatar.getWidth(), imageAvatar.getHeight());

        Graphics2D graphics = bufferedImageAvatar.createGraphics();
        bufferedImageAvatar = graphics.getDeviceConfiguration().createCompatibleImage(imageAvatar.getWidth(), imageAvatar.getHeight(), Transparency.TRANSLUCENT);

        graphics = bufferedImageAvatar.createGraphics();

        graphics.setComposite(AlphaComposite.Clear);
        graphics.fill(new Rectangle(bufferedImageAvatar.getWidth(), bufferedImageAvatar.getHeight()));
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        graphics.setClip(shape);

        graphics.drawImage(imageAvatar, 0, 0, null);
        graphics.dispose();

        return bufferedImageAvatar;
    }

    /**
     * 二维码和logo合成
     * @throws Exception
     */
    public static void qrcodeLogoComposite() throws Exception {
        QrcodeHandler qrcodeHandler = new QrcodeHandler();
        String qrCodeText = "http://localhost/rebateGame/index.html?shareId=" + 2222;
        String imagePngValue = MediaType.IMAGE_PNG_VALUE;
        String qrcodeFormat = MediaType.IMAGE_PNG.getSubtype();

        int qrcodeWidth = 300;
        int qrcodeHeight = 300;

        Random random = new Random();
        String qrcodePath = "D:\\image_composite\\"+ random.nextInt() + "." + qrcodeFormat;

        BufferedImage qRCodeBufferedImage = qrcodeHandler.getQrCode(qrCodeText, qrcodeWidth, qrcodeHeight);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        File logoFile = new File("D:\\image_composite\\logo.png");
        FileInputStream fileInputStream = new FileInputStream(logoFile);
        BufferedImage bufferedImage = qrcodeHandler.genQRCodeLogo(qRCodeBufferedImage, fileInputStream);


        File qrcodeFile = new File(qrcodePath);
        ImageIO.write(bufferedImage, qrcodeFormat, qrcodeFile);

        String absolutePath = qrcodeFile.getAbsolutePath();

        System.out.println(absolutePath);
    }

    /**
     * 生成二维码
     * @throws Exception
     */
    public static void qrcodeGen() throws Exception {
        String qrCodeText = "http://localhost/rebateGame/index.html?shareId=" + 2222;
        String qrcodeFormat = MediaType.IMAGE_PNG.getSubtype();

        int qrcodeWidth = 300;
        int qrcodeHeight = 300;

        Random random = new Random();
        String qrcodePath = "D:\\image_composite\\"+ random.nextInt() + "." + qrcodeFormat;

        Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.MARGIN, 1);// 二维码与图片边距

        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeText, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);

        File qrcodeFile = new File(qrcodePath);
        MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, qrcodeFile.toPath());

        String absolutePath = qrcodeFile.getAbsolutePath();

        System.out.println(absolutePath);
    }

}
