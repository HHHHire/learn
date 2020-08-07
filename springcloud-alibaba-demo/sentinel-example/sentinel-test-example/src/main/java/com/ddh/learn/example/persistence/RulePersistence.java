package com.ddh.learn.example.persistence;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * sentinel规则持久化到文件中，这里只有流控规则，其余规则的方式类似
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 14:34
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class RulePersistence implements InitFunc {
    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void init() throws Exception {
        String ruleDir = System.getProperty("user.dir") + "/sentinel-rules/" + appName;
        String flowRulePath = ruleDir + "/flow-rule.json";
        System.out.println(flowRulePath);
        createDir(ruleDir);
        createFile(flowRulePath);

        ReadableDataSource<String, List<FlowRule>> dataSource = new FileRefreshableDataSource<>(flowRulePath, flowRuleListParser);
        FlowRuleManager.register2Property(dataSource.getProperty());
        FileWritableDataSource<List<FlowRule>> writableDataSource = new FileWritableDataSource<>(flowRulePath, this::encoding);
        WritableDataSourceRegistry.registerFlowDataSource(writableDataSource);

    }

    private Converter<String, List<FlowRule>> flowRuleListParser = source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
    });

    private void createDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private <T> String encoding(T t) {
        return JSON.toJSONString(t);
    }
}
