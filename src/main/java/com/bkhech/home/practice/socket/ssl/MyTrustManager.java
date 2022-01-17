package com.bkhech.home.practice.socket.ssl;

import javax.net.ssl.X509TrustManager;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 自签证书验证，需要自行实现X509TrustManager中的checkServerTrusted()回调函数
 *  当然也可以不验证，直接实现为空即可
 * @author guowm
 * @date 2022/1/4
 */
public class MyTrustManager implements X509TrustManager {
    Certificate certificate;
    public MyTrustManager(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        Exception error = null;
        if (null == chain || 0 == chain.length) {
            error = new CertificateException("Certificate chain is invalid.");
        } else if (null == authType || 0 == authType.length()) {
            error = new CertificateException("Authentication type is invalid.");
        } else {
            try {
                /* 自签名，服务端只发一个证书，可以不用检查证书链 */
                // 检查证书是否过期
                chain[0].checkValidity();
                // 验证是否使用了指定公钥相对应的私钥签署了此证书
                chain[0].verify(certificate.getPublicKey());
            } catch (NoSuchAlgorithmException e) {
                error = e;
            } catch (NoSuchProviderException e) {
                error = e;
            } catch (SignatureException e) {
                error = e;
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        if (null != error) {
            throw new CertificateException(error);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
