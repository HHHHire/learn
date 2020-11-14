package com.ddh.learn.demo.bean.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 0:38
 */
@Data
public class ReasonParam implements Serializable {
    private String text;
    private List<BaseBean> files;
}
