package com.bkhech.home.practice.javacore.image_composite.drawpicture;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2019/11/29
 */
public class QrcodeHandler {

    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    public BufferedImage genQRCodeLogo(BufferedImage image, InputStream fileInputStream) throws IOException {

        BufferedImage logo = ImageIO.read(fileInputStream);
        Graphics2D g = image.createGraphics();

        int widthLogo = logo.getWidth();
        int heightLogo = logo.getHeight();
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - logo.getHeight()) / 2;

        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g.setStroke(stroke);
        g.drawImage(logo, x, y, widthLogo, heightLogo, null);
//        g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
        g.setColor(Color.WHITE);

        g.dispose();

        return image;
    }

    public BufferedImage getQrCode(String text, int width, int height) throws WriterException {

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        return toBufferedImage(bitMatrix);
    }

    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}
