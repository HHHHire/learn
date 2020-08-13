package com.ddh.learn.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/13 11:28
 */
public class SpringFlowable {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("flowable-spring.xml");
        RepositoryService repositoryService = (RepositoryService) ac.getBean("repositoryService");
        // 部署流程定义
        repositoryService.createDeployment()
                .addClasspathResource("hello.bpmn20.xml")
                .deploy();
        ProcessEngine processEngine = (ProcessEngine) ac.getBean("engineProcess");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 创建流程实例
        ProcessInstance helloProcess = runtimeService.startProcessInstanceByKey("helloProcess");
    }
}
