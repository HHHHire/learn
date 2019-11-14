package com.jdk;

import com.entity.ISinger;
import com.entity.Singer;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ddh
 * @date: 2019/10/24  16:01
 * @description:
 */
public class JdkProxy {
    @Test
    public void test1() {
        final Singer singer = new Singer();
        ISinger iSinger = (ISinger) Proxy.newProxyInstance(
                singer.getClass().getClassLoader(),
                singer.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("start");
                        Object invoke = method.invoke(singer, args);
                        System.out.println("end");
                        return invoke;
                    }
                }
        );
        iSinger.sing();
    }
}
