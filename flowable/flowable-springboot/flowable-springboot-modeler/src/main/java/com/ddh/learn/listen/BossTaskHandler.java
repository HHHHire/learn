package com.ddh.learn.listen;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/1 23:34
 */
public class BossTaskHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("老板");
    }
}
