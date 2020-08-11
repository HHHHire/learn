package com.ddh.learn.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/7 23:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {
    public Long id;
    public String userId;
    public String commodityCode;
    public Integer counts;
    public Integer money;
}
