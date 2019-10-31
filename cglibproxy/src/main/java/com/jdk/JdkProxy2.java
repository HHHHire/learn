package com.jdk;

import com.entity.Singer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: ddh
 * @date: 2019/10/24  16:21
 * @description:
 */
public class JdkProxy2 implements InvocationHandler {

    Object target;

    public JdkProxy2(Object target){
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        Object invoke = method.invoke(target, args);
        System.out.println("end");
        return invoke;
    }
}
