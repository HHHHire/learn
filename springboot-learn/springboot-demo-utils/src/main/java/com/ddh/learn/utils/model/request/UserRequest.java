package com.ddh.learn.utils.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/14 0:46
 */
@Data
public class UserRequest implements Serializable {
    private String id;
    private String name;
    private List<String> address;
}
