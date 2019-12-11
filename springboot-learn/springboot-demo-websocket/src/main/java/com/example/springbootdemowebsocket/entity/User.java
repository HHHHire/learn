package com.example.springbootdemowebsocket.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: ddh
 * @data: 2019/11/29 9:31
 * @description
 **/
@Data
@Builder
public class User implements Serializable {
    private static final Long serialVersionUid = -123112312234123L;
    private String userId;
    private String userName;
}
