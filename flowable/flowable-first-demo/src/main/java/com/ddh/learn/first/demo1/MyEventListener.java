package com.ddh.learn.first.demo1;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/12 16:47
 * 自定义事件监听器
 */
public class MyEventListener implements FlowableEventListener {

    private static final String JOB_EXECUTION_SUCCESS = "JOB_EXECUTION_SUCCESS";
    private static final String JOB_EXECUTION_FAILURE = "JOB_EXECUTION_FAILURE";

    @Override
    public void onEvent(FlowableEvent event) {
        if (JOB_EXECUTION_SUCCESS.equals(event.getType().name())) {
            System.out.println("successful ... ");
        } else if (JOB_EXECUTION_FAILURE.equals(event.getType().name())) {
            System.out.println("failed ... ");
        }
        System.out.println("event type is : " + event.getType().name());
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
