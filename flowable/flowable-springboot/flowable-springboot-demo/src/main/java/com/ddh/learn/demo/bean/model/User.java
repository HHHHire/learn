package com.ddh.learn.demo.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 0:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public static final String PRINCIPAL_ATTRIBUTE_NAME = User.class.getName() + ".PRINCIPAL";
    private String id;
    private String name;
    private String group;
}
