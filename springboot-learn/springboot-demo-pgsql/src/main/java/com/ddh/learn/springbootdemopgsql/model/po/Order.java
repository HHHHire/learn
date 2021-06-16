package com.ddh.learn.springbootdemopgsql.model.po;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/28 16:01
 * @description:
 */
@Entity
@Data
@Builder
@Table(name = "z_order")
public class Order extends BasePo {
    private String username;
    private String addr;
}
