package com.ddh.learn.utils;

import java.math.BigDecimal;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 15:13
 * @description:
 */
public final class BigDecimalUtil {
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        // 精度取2位，规则为四舍五入
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        System.out.println(BigDecimalUtil.div(1, 3));
    }
}
