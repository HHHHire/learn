package com.ddh.learn.springbootdemopgsql.model.test;

import com.ddh.learn.springbootdemopgsql.model.BasePo;
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
@Table(name = "z_order")
public class Order extends BasePo {
    private String username;
    private String addr;
}
