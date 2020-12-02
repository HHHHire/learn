package com.ddh.learn.demo.bean.model;

import com.ddh.learn.demo.bean.enums.CandidateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 22:58
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateParam implements Serializable {
    private static final long serialVersionUID = 994290783964598993L;
    public String id;
    public String name;
    public CandidateType type = CandidateType.USER;
}
