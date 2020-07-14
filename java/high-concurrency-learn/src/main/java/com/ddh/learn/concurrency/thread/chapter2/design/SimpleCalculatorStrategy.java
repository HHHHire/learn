package com.ddh.learn.concurrency.thread.chapter2.design;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:32
 * @description
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private final static double SALARY_TAX = 0.1;

    private final static double BOUDS_TAX = 0.15;

    public double calculate(double salary, double bounds) {
        return salary * SALARY_TAX + bounds * BOUDS_TAX;
    }
}
