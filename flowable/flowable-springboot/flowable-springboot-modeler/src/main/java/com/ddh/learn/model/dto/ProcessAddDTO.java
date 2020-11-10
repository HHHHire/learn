package com.ddh.learn.model.dto;

import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/2 10:15
 */
@Data
public class ProcessAddDTO {
    private String name;
    private String key;
    private String description;
    private Integer modelType;
}
