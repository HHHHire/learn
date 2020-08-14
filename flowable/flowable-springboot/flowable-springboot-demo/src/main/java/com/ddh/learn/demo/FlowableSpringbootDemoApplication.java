package com.ddh.learn.demo;

import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author deng
 */
@SpringBootApplication
public class FlowableSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringbootDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final TaskService taskService,
                                  final RuntimeService runtimeService,
                                  IdentityService identityService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("Number of process define: " + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of task : " + taskService.createTaskQuery().count());
                runtimeService.startProcessInstanceByKey("oneTaskProcess");
                System.out.println("After start process Number of task : " + taskService.createTaskQuery().count());

                System.out.println("========================================");
                List<Execution> alert = runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").list();
                System.out.println(alert);
            }
        };
    }
}
