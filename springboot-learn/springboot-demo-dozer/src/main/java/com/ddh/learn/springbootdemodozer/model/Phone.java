package com.ddh.learn.springbootdemodozer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 15:56
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String phoneNum;
}
