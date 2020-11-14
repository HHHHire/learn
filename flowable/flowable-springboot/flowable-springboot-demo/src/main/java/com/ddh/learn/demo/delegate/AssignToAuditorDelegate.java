package com.ddh.learn.demo.delegate;

import com.ddh.learn.demo.bean.enums.AuditStatus;
import com.ddh.learn.demo.bean.model.CandidateParam;
import com.ddh.learn.demo.utils.FlowUtil;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 20:35
 * @desc: 分配审批人，切换到下一个审批人
 */
public class AssignToAuditorDelegate implements JavaDelegate {

    /**
     * delegate - 分配审批人
     * <br>
     * 整体流程：
     * 1.创建申请
     * 2.分配给审批人（需要审批人列表，当前审批人）
     * -> 有下一个审批人 -> 3
     * -> 无 -> 4
     * 3.审批人审批
     * -> 同意 -> 2
     * -> 拒绝 -> 5
     * 4.存储数据
     * 5.结束
     */
    @Override
    public void execute(DelegateExecution execution) {
        // 初始化变量，清空临时变量
        execution.removeVariable(FlowUtil.APPROVED_KEY);
        execution.removeVariable(FlowUtil.AUDITOR_KEY);
        execution.setVariable(FlowUtil.AUDIT_STATUS_KEY, AuditStatus.WAIT_AUDIT.toString());

        // 拿到审批人列表
        List<CandidateParam> candidateList = FlowUtil.getCandidateList(execution);
        Integer index = execution.getVariable(FlowUtil.AUDITOR_IDX_KEY, Integer.class);
        if (index == null) {
            index = 0;
        } else if (index+1 >= candidateList.size()) {
            return;
        } else {
            index++;
        }

        CandidateParam candidateParam = candidateList.get(index);
        FlowUtil.setCandidate(execution, candidateParam);
        execution.setVariable(FlowUtil.AUDITOR_IDX_KEY, index);
    }
}
