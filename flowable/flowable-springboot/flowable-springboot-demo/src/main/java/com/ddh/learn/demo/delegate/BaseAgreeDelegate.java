package com.ddh.learn.demo.delegate;

import com.ddh.learn.demo.bean.enums.AuditStatus;
import com.ddh.learn.demo.utils.FlowUtil;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 23:19
 */
public class BaseAgreeDelegate implements JavaDelegate {

    private Logger log = LoggerFactory.getLogger(BaseAgreeDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        log.info("{}已被同意", execution.getVariables());
        FlowUtil.end(execution, AuditStatus.AGREE_AUDIT);
    }
}
