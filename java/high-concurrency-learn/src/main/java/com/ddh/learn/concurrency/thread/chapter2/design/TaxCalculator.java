package com.ddh.learn.concurrency.thread.chapter2.design;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:26
 * @description 税务计算器
 */
public class TaxCalculator {

    /**
     * 工资
     */
    private final double salary;

    /**
     * 奖金
     */
    private final double bounds;

    private final CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bounds, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bounds = bounds;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax() {
        return calculatorStrategy.calculate(getSalary(), getBounds());
    }

    public double calculate() {
        return this.calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBounds() {
        return bounds;
    }
}
