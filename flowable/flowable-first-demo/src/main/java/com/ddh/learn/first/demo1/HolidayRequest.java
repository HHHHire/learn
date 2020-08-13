package com.ddh.learn.first.demo1;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/12 14:08
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode", "unused"})
public class HolidayRequest {
    public static void main(String[] args) {
        // 定义配置
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 创建流程引擎
        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 创建新的部署流程，即流程定义
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        // 新建查询
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploy.getId())
                .singleResult();
        // 打印输出流程名称
        System.out.println("found process define: " + processDefinition.getName());

        /*===========================流程过程开始=============================*/
        /*
         * 启动流程实例，这里通过Scanner获取变量
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("你是谁");
        String employee = scanner.nextLine();

        System.out.println("你要请假多久(天)");
        Integer countsOfDays = Integer.valueOf(scanner.nextLine());

        System.out.println("理由");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("employee", employee);
        map.put("countsOfDays", countsOfDays);
        map.put("description", description);
        // 添加事件监听器
//        runtimeService.addEventListener(new MyEventListener());
        // 创建流程实例，携带输入的信息map
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", map);

        TaskService taskService = processEngine.getTaskService();
        // 获取所有的任务
        List<Task> list = taskService.createTaskQuery().taskCandidateGroup("manager").list();
        System.out.println("你有" + list.size() + "个任务");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ")" + list.get(i).getName());
        }

        System.out.println("你要处理哪个任务");
        int index = Integer.parseInt(scanner.nextLine());
        Task task = list.get(index - 1);
        // 获取任务中的信息map
        Map<String, Object> variables = taskService.getVariables(task.getId());
        System.out.println(variables.get("employee") + "想要请假" + variables.get("countsOfDays") + "天，理由是：" +
                variables.get("description") + "是否同意(Y/N)");
        boolean approved = "y".equals(scanner.nextLine().toLowerCase());
        map = new HashMap<>(1);
        map.put("approved", approved);
        taskService.complete(task.getId(), map);

        /*===========================流程过程结束=============================*/

        /*查看每个阶段用时*/
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> instances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        for (HistoricActivityInstance instance : instances) {
            System.out.println(instance.getActivityId() + "用了:" + instance.getDurationInMillis() + "ms");
        }

        /*API查询*/
        List<Task> list1 = taskService.createTaskQuery()
                // 输入的用户名
                .taskAssignee("li")
                .processVariableValueEquals("description", "wu").list();
//        System.out.println(list1.get(0).getAssignee());

        /*原生sql查询，更加灵活;获取managementService对表操作*/
        ManagementService managementService = processEngine.getManagementService();
        // 在ACT_RU_TASK表中查找name属性为Holiday approved的字段
        List<Task> list2 = taskService.createNativeTaskQuery()
                .sql("select * from " + managementService.getTableName(Task.class) +
                        " T where T.NAME_ = #{taskName}")
                .parameter("taskName", "Holiday approved")
                .list();
        System.out.println(list2);
    }
}
