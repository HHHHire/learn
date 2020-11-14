package com.ddh.learn.demo.bean.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 0:28
 */
@Data
public class ProcessParam {
    /**
     * 自定义类型
     */
    private String type;

    /**
     * 流程id，是xml文件里的那个processId
     */
    private String processId = "standardRequest";

    /**
     * 审批人
     */
    private List<CandidateParam> auditors;

    /**
     * 其他参数
     */
    private Map<String, Object> params;

    /**
     * 文件材料
     */
    private List<BaseBean> files;
}
