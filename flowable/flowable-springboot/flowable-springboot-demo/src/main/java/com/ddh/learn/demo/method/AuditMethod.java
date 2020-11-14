package com.ddh.learn.demo.method;

import com.ddh.learn.demo.bean.enums.CandidateType;
import com.ddh.learn.demo.bean.model.CandidateParam;
import com.ddh.learn.demo.utils.FlowUtil;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 21:48
 */
@Component
public class AuditMethod {
    /**
     * 是否存在审批者
     */
    public boolean existAuditor(DelegateExecution execution) {
        return FlowUtil.existAuditor(execution);
    }

    /**
     * 获取当前审批者
     */
    public CandidateParam getCurrentAuditor(DelegateExecution execution) {
        return FlowUtil.getCandidateFromExecution(execution).orElse(null);
    }

    /**
     * 获取当前审批者id
     */
    public String getCurrentAuditorId(DelegateExecution execution) {
        CandidateParam auditor = getCurrentAuditor(execution);
        if (auditor.getName() != null && auditor.getType() == CandidateType.USER) {
            return auditor.getId();
        }
        return null;
    }

    /**
     * 获取当前审批组 id
     */
    public String getCandidateGroups(DelegateExecution execution) {
        CandidateParam auditor = getCurrentAuditor(execution);
        if (auditor.getName() != null && auditor.getType() == CandidateType.GROUP) {
            return auditor.getId();
        }
        return null;
    }

    /**
     * 是否同意申请
     */
    public boolean isApproved(DelegateExecution execution) {
        return FlowUtil.isApproved(execution);
    }
}
