package com.ddh.learn.rocketmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 10:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private String msg;
}
