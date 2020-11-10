package com.ddh.learn.java.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 14:35
 */
@Data
public class User implements Serializable {
    private String name;
    private Phone phone;

    @Data
    public class Phone {
        private String number;
        private Price price;
    }

    @Data
    public class Price {
        private Double cost;
    }
}
