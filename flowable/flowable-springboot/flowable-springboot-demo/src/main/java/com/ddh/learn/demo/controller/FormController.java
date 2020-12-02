package com.ddh.learn.demo.controller;

import com.ddh.learn.demo.utils.ApiResult;
import org.flowable.engine.FormService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.form.StartFormData;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.model.FormField;
import org.flowable.form.model.SimpleFormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 18:12
 */
@RestController
@RequestMapping("form")
public class FormController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FormService formService;
    @Autowired
    private FormRepositoryService formRepositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @GetMapping
    public ApiResult getForm(@RequestParam(defaultValue = "formRequest") String formId) {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(formId)
                .latestVersion().singleResult();
        StartFormData form = formService.getStartFormData(pd.getId());
        FormInfo info = formRepositoryService.getFormModelByKey(form.getFormKey());
        return ApiResult.withData(ApiResult.Code.SUCCESS, info.getFormModel());
    }

    @PostMapping
    public ApiResult start(String formId, String outcome, HttpServletRequest request) {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(formId)
                .latestVersion().singleResult();
        StartFormData form = formService.getStartFormData(pd.getId());
        SimpleFormModel info = (SimpleFormModel) formRepositoryService.getFormModelByKey(form.getFormKey()).getFormModel();

        Map<String, Object> properties = new HashMap<>();

        for(FormField field : info.getFields()) {
            properties.putIfAbsent(field.getId(), request.getParameter(field.getId()));
        }

        ProcessInstance pi = runtimeService.startProcessInstanceWithForm(pd.getId(), outcome, properties, pd.getName());
        return ApiResult.withData(ApiResult.Code.SUCCESS, pi.getId());
    }
}
