/*
package cn.etu.jxust.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

*/
/**
 * @author ddh
 * @date 2019/8/25 12:14
 * @description
 **//*

public class BeanFactory {
    private static Properties ps;
    private static Map<String, Object> beans;

    static {

        try {
            ps = new Properties();
            // 获取properties 文件输入流
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            ps.load(in);
            beans = new HashMap<String, Object>();
            Enumeration<Object> keys = ps.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = ps.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String name){
        return beans.get(name);
    }

//    public static Object getBean(String name) {
//        Object bean = null;
//
//        String beanPath = ps.getProperty(name);
//        try {
//            bean = Class.forName(beanPath).newInstance();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }
}
*/
