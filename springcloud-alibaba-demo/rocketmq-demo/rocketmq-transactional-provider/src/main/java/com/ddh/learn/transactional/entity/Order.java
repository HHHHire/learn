package com.ddh.learn.transactional.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 17:35
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private String userName;
    private Double pay;
}
