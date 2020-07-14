package com.ddh.learn.concurrency.thread.chapter2.design;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:31
 * @description 策略模式
 */
@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary, double bounds);
}
