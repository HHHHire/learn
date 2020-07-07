package com.ddh.learn.springbootdemodozer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/7 13:41
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
//    private String address;
    private Integer age;
    private Set<Phone> phone;
}
