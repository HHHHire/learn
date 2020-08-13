package com.ddh.learn.first.demo1;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/12 15:39
 * externalSystemCall阶段调用
 */
public class CallExternalSystemDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println(delegateExecution.getVariable("employee") + "调用了");
    }
}
