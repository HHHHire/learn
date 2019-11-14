package com.test;

import com.cglib.ProxyFactory;
import com.entity.Dancer;
import com.entity.ISinger;
import com.entity.Singer;
import com.jdk.JdkProxy2;
import com.sun.prism.j2d.J2DPipeline;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author: ddh
 * @date: 2019/10/24  15:26
 * @description:
 */
public class ProxyTest {
    @Test
    public void test1() {
        Dancer dancer = new Dancer();
        ProxyFactory proxyFactory = new ProxyFactory(dancer);
        Dancer proxyInstance = (Dancer)proxyFactory.getProxyInstance();
        proxyInstance.dancer();
    }

    @Test
    public void testJdk2(){
        ISinger singer = new Singer();
        ISinger iSinger = (ISinger)Proxy.newProxyInstance(
                Singer.class.getClassLoader(),
                Singer.class.getInterfaces(),
                new JdkProxy2(singer)
        );
        iSinger.sing();
    }

    @Test
    public void test3() {
        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = s2.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
    }
}
