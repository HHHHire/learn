package com.ddh.learn.poi.base.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/20 9:50
 * @description: 工人
 */
@Data
@Builder
public class Labor {
    private String category;
    private int people;
    private int price;
    private int totalPrice;
}
