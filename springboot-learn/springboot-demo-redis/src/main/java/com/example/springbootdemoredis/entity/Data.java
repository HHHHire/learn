package com.example.springbootdemoredis.entity;

import com.example.springbootdemoredis.util.SerializeUtil;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author: ddh
 * @data: 2019/11/20 14:50
 * @description
 **/
@lombok.Data
@Builder
public class Data implements Serializable {
    private String userName;
    private Integer userAge;
    private String userAddress;
}
