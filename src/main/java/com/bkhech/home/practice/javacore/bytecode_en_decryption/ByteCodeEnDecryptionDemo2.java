package com.bkhech.home.practice.javacore.bytecode_en_decryption;

import java.io.*;

/**
 * 对字节码加密解码 样例
 *   加密的时候按照指定算法对字节码进行运算，解密的时候进行逆运算
 *
 * @author guowm
 * @date 2022/2/16
 */
public class ByteCodeEnDecryptionDemo2 {
    final static int secretKey = 123;
    final static String userDir = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
//        testJpg();
        testTxt();
    }

    private static void testJpg() throws IOException {
        long random = System.currentTimeMillis();
        // 源文件名
        String sourceFileName = "GA166781.jpg";
        // 加密文件名
        String encryptionFileName = "GA166781_" + random + ".jpg";
        // 解密加密文件后文件名
        String decryptionFileName = "GA166781_" + random + "_decryption.jpg";

        encryption(sourceFileName, encryptionFileName);
        decryption(encryptionFileName, decryptionFileName);
    }

    private static void testTxt() throws IOException {
        long random = System.currentTimeMillis();
        // 源文件名
        String sourceFileName = "test.txt";
        // 加密文件名
        String encryptionFileName = "test_" + random + ".txt";
        // 解密加密文件后文件名
        String decryptionFileName = "test_" + random + "_decryption.txt";

        encryption(sourceFileName, encryptionFileName);
        decryption(encryptionFileName, decryptionFileName);
    }

    private static void encryption(String sourceFileName, String encryptionFileName) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(userDir + "/www/" + sourceFileName));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b;
        while ((b = bis.read()) != -1) {
            baos.write(b);
        }

        final byte[] bytes = baos.toByteArray();
        encryptionByte(bytes);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(userDir + "/www/" + encryptionFileName));
        for (int i = 0; i < bytes.length; i++) {
            bos.write(bytes[i]);
        }

        bis.close();
        bos.close();
    }

    private static void decryption(String encryptionFileName, String decryptionFileName) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(userDir + "/www/" + encryptionFileName));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b;
        while ((b = bis.read()) != -1) {
            baos.write(b);
        }

        final byte[] bytes = baos.toByteArray();
        encryptionByte(bytes);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(userDir + "/www/" + decryptionFileName));

        for (int i = 0; i < bytes.length; i++) {
            bos.write(bytes[i]);
        }

        bis.close();
        bos.close();
    }

    /**
     * 加密
     * @param buf
     */
    private static void encryptionByte(byte[] buf) {
        int key = secretKey % 255;
        for (int i = 0; i < buf.length; ++i) {
            int c = buf[i];
            c += key;
            if (c > 255) {
                c -= 256;
            }
            buf[i] = (byte) (c ^ 0xff);
        }
    }

    /**
     * 解密
     * @param buf
     */
    private static void decryptionByte(byte[] buf) {
        int key = secretKey % 255;
        for (int i = 0; i < buf.length; ++i) {
            int c = (byte) (buf[i] ^ 0xff);
            c = c >= key ? c - key : 256 + c - key;
            buf[i] = (byte) c;
        }
    }


}
