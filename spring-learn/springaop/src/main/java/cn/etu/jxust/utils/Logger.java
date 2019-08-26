package cn.etu.jxust.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author ddh
 * @date 2019/8/26 15:27
 * @description
 **/
public class Logger {
//    public void printLog(){
//        System.out.println("Logger类中的printLog()开始了...");
//    }

    public void beforeLog() {
        System.out.println("前置通知");
    }

    public void afterRetrunLog() {
        System.out.println("后置通知");
    }

    public void afterThrowingLog() {
        System.out.println("异常通知");
    }

    public void afterLog() {
        System.out.println("最终通知");
    }

    public Object arrountLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        Object[] args = pjp.getArgs();
        try {
            System.out.println("前置通知");
            // 切入点方法
            rtValue = pjp.proceed(args);
            System.out.println("后置通知");
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("异常通知");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("最终通知");
        }
    }
}
