package com.ddh.learn.springbootdemopgsql.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/8 10:43
 * @description
 */
@Data
@Builder
public class AuthorDTO {
    private String name;

    private String hometown;

}
