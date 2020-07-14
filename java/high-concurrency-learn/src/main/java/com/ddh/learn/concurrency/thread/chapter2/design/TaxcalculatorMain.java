package com.ddh.learn.concurrency.thread.chapter2.design;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 22:30
 * @description 此处的应用就是把可能需要经常改变的代码，抽取出来，方便以后修改
 */
public class TaxcalculatorMain {
    public static void main(String[] args) {
        /*TaxCalculator taxCalculator = new TaxCalculator(10000, 2000, new SimpleCalculatorStrategy());
        System.out.println(taxCalculator.calculate());*/

        TaxCalculator taxCalculator = new TaxCalculator(10000d, 2000d, (s, b) -> s * 0.1d + b * 0.15d);
        System.out.println(taxCalculator.calculate());
    }
}
