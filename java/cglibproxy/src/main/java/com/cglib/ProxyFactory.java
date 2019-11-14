package com.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: ddh
 * @date: 2019/10/24  15:19
 * @description:
 */
public class ProxyFactory implements MethodInterceptor {

    /**
     * 维护目标对象
     */
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    /**
     * 给目标对象创建一个代理对象
     * @return 代理对象
     */
    public Object getProxyInstance(){
        // 1.工具类
        Enhancer enhancer = new Enhancer();
        // 2.设置父类
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数
        enhancer.setCallback(this);
        // 4.创建子类（代理对象）
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        Object invoke = method.invoke(target, objects);
        System.out.println("end");
        return invoke;
    }
}
