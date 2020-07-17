package com.ddh.learn.springbootdemodozer.model.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/17 10:56
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode implements Serializable {
    private Long id;
    private String name;
    private List<TreeNode> child;
}
