package com.ddh.learn.springbootdemodozer.model.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/17 10:58
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageNode implements Serializable {
    private String name;
    private String fullPath;
    /**
     * 是否为叶子节点
     */
    private Boolean leafNode;

    private List<ManageNode> children;
}
