package com.ddh.learn.poi.base.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/21 17:14
 * @description:
 */
@Data
@Builder
public class Report {
    private Integer month;
    private Integer day;
    private String week;
}
