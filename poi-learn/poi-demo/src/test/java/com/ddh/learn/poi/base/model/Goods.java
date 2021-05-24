package com.ddh.learn.poi.base.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/20 9:50
 * @description: 货物
 */
@Data
@Builder
public class Goods {
    private int count;
    private String name;
    private String desc;
    private int discount;
    private int tax;
    private int price;
    private int totalPrice;
}
