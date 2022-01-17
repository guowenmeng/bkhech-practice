package com.bkhech.home.practice.socket.ssl;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * 入口
 * {@literal https://www.jianshu.com/p/ecad4d5a461d}
 * @author guowm
 * @date 2022/1/4
 */
public class SslMain {
    public static void main(String[] args) {
        try {
            ssl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ssl() throws IOException {
//        URL url = new URL("http://localhost:8080");
//        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        /**
         *  信任域名，否则域名必须跟ca证书域名内容一致
         *  javax.net.ssl.SSLHandshakeException: java.security.cert.CertificateException:
         *      No name matching localhost found
         */
        HostnameVerifier hnv = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                // Always return true，接受任意域名服务器
                return true;
            }
        };

        /**
         * javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException:
         *      PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
         *      unable to find valid certification path to requested target
         */
        //1、加载预制的root.crt根证书。root.crt用于验证服务端发送的证书是否有效，有没有被中间人篡改。
        Certificate certificate = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = new BufferedInputStream(
                    new FileInputStream("C:\\Users\\guowm\\Downloads\\apache-tomcat-9.0.16\\conf\\root.crt"));
            certificate = cf.generateCertificate(caInput);
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        //2、添加PKCS12格式秘钥库，用于客户端发送证书给服务端
        KeyManager[] keyManagers = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(
                    new FileInputStream("C:\\Users\\guowm\\Downloads\\apache-tomcat-9.0.16\\conf\\client.p12"),
                    "123456".toCharArray()// 密钥库的密码
            );
            String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(defaultAlgorithm);
            kmf.init(keyStore, "123456".toCharArray()); // 密钥库的密码
            keyManagers = kmf.getKeyManagers();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        //3、自实现X509TrustManager的checkServerTrusted()。
        //自签证书验证，需要自行实现X509TrustManager中的checkServerTrusted()回调函数，即下文的MyTrustManager。
        //添加自定义证书校验MyTrustManager
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            TrustManager[] tm = {new MyTrustManager(certificate)};
            sslcontext.init(null, tm, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        URL url = new URL("https://localhost:8443");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setHostnameVerifier(hnv);
        urlConnection.setSSLSocketFactory(sslcontext.getSocketFactory());
        InputStream in = urlConnection.getInputStream();
    }
}
