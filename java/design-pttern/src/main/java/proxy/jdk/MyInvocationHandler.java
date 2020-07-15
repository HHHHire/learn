package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/15 17:46
 * @desc 处理器对象
 */
public class MyInvocationHandler implements InvocationHandler {

    private static final String SELL = "sell";
    private static final String SAY = "say";

    private RealSubject realSubject;

    MyInvocationHandler(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理类");
        if (SELL.equals(method.getName())) {
            System.out.println("调用的是卖书的方法");
            return method.invoke(realSubject, args);
        } else if (SAY.equals(method.getName())) {
            System.out.println("调用的是说话的方法");
            return method.invoke(realSubject, args);
        }
        return null;
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        Subject o = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);
        o.sell();
        o.say();
    }
}
