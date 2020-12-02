package com.ddh.learn.demo.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 0:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReasonParam implements Serializable {
    private String text;
    private List<BaseBean> files;
}
