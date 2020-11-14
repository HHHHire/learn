package com.ddh.learn.demo.delegate;

import com.ddh.learn.demo.bean.enums.AuditStatus;
import com.ddh.learn.demo.utils.FlowUtil;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 21:16
 */
public class BaseRejectDelegate implements JavaDelegate {
    private Logger log = LoggerFactory.getLogger(BaseRejectDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        log.info("{}已被拒绝", execution.getVariables());
        FlowUtil.end(execution, AuditStatus.REJECT_AUDIT);
    }
}
