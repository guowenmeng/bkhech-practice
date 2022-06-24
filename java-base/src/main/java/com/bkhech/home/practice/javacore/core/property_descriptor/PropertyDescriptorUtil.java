package com.bkhech.home.practice.javacore.core.property_descriptor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 属性描述器示例 {@link PropertyDescriptor}
 *
 * @author guowm
 * @date 2022/4/2
 */
//public class PropertyDescriptorUtil {
//
//    public static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) {
//        PropertyDescriptor pd = null;
//        try {
//            //根据字段名来获取字段
//            Field f = clazz.getDeclaredField(propertyName);
//            if (f != null) {
//                pd = new PropertyDescriptor(propertyName, clazz );
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return pd;
//    }
//
//    public static void setProperty(Object obj, String propertyName, Object value) {
//        //获取对象的类型
//        Class clazz = obj.getClass();
//        //获取 clazz 类型中的 propertyName 的属性描述器
//        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
//        //从属性描述器中获取 set 方法
//        Method setMethod = pd.getWriteMethod();
//        try {
//            //调用 set 方法将传入的value值保存属性中去
//            setMethod.invoke(obj, value);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Object getProperty(Object obj, String propertyName) {
//        //获取对象的类型
//        Class clazz = obj.getClass();
//        //获取 clazz 类型中的 propertyName 的属性描述器
//        PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);
//        //从属性描述器中获取 get 方法
//        Method getMethod = pd.getReadMethod();
//        Object value = null;
//        try {
//            //调用方法获取方法的返回值
//            value = getMethod.invoke(clazz, new Object[]{});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//
//    public static void main(String[] args) throws Exception {
//        //这里的类名是全名。。有包的话要加上包名
//        Class clazz = Class.forName("com.bkhech.home.practice.javacore.core.property_descriptor.PropertyDescriptorDto");
//        Object obj = clazz.newInstance();
//        Field[] fields = clazz.getDeclaredFields();
//        //写数据
//        for (Field f : fields) {
//            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
//            //获得写方法
//            Method wM = pd.getWriteMethod();
//            //因为知道是int类型的属性，所以传个int过去就是了。。实际情况中需要判断下他的参数类型
//            wM.invoke(obj, 2);
//        }
//        //读数据
//        for (Field f : fields) {
//            PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
//            //获得读方法
//            Method rM = pd.getReadMethod();
//            //因为知道是int类型的属性,所以转换成integer就是了。。也可以不转换直接打印
//            Integer num = (Integer) rM.invoke(obj);
//            System.out.println(num);
//        }
//    }
//}

