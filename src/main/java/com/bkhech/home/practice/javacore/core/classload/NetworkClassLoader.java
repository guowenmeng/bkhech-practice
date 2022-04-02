package com.bkhech.home.practice.javacore.core.classload;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @author guowm
 * @date 2020/7/30
 * @description
 */
public class NetworkClassLoader extends ClassLoader{
    private final String rootUrl;

    public NetworkClassLoader(String rootUrl) {
        super();
        this.rootUrl = rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //父类是否已经加载
        Class<?> clazz = super.findLoadedClass(name);
        //父类未加载
        if (Objects.isNull(clazz)) {
            //根据类的名称，获取该class文件的字节码数组
            byte[] classData = getClassData(name);
            if (Objects.isNull(classData)) {
                throw new ClassNotFoundException();
            }
            //将class字节码数组转化成Class类的实例
            clazz = super.defineClass(name, classData, 0 , classData.length);
        }
        return clazz;
    }

    private byte[] getClassData(String name) {
        InputStream inputStream = null;
        try {
            String path = classNameToPath(name);
            inputStream = new FileInputStream(path);

            byte[] buff = new byte[1024*10];
            int len = -1;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buff)) != -1) {
                byteArrayOutputStream.write(buff, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private String classNameToPath(String name) {
        String[] split = name.split("\\.");
        return rootUrl +"/" + split[split.length - 1].replaceAll("\\.", "/") + ".class";
    }

}
