package com.ddh.learn.sequence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/14 11:15
 */
@Data
@Builder
@AllArgsConstructor
public class Order implements Serializable {
    private String id;
    private Integer price;
    private Integer counts;
}
