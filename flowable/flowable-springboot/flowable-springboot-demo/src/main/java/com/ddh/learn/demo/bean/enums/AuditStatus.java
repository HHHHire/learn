package com.ddh.learn.demo.bean.enums;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 21:19
 * @desc: 申请状态
 */
public enum AuditStatus {
    /**
     * 待审批
     */
    WAIT_AUDIT,
    /**
     * 同意申请
     */
    AGREE_AUDIT,
    /**
     * 拒绝申请
     */
    REJECT_AUDIT,
    /**
     * 已取消
     */
    CANCEL
}
