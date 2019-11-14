package com.jdk;

import com.entity.ISinger;
import com.entity.Singer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ddh
 * @date: 2019/10/24  14:54
 * @description: 动态代理 需要实现接口
 */
public class JdkProxy {
    public static void main(String[] args) {
        Singer target = new Singer();
        ISinger proxy = (ISinger)Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (o, method, args1) -> {
                    System.out.println("start");
                    Object invoke = method.invoke(target, args1);
                    System.out.println("end");
                    return invoke;
                }
        );
        proxy.sing();
    }
}
