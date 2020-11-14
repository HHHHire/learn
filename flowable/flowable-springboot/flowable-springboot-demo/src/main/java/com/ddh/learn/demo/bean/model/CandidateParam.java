package com.ddh.learn.demo.bean.model;

import com.ddh.learn.demo.bean.enums.CandidateType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 22:58
 */
@Data
public class CandidateParam implements Serializable {
    public String id;
    public String name;
    public CandidateType type = CandidateType.USER;
}
