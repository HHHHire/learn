package com.ddh.learn.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddh.learn.demo.bean.enums.AuditStatus;
import com.ddh.learn.demo.bean.model.CandidateParam;
import com.ddh.learn.demo.bean.model.ProcessParam;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/14 21:54
 */
public class FlowUtil {
    /**
     * 审批人列表
     */
    public static final String AUDITOR_LIST_KEY = "AUDITOR_LIST";

    /**
     * 当前审批人
     */
    public static final String AUDITOR_KEY = "AUDITOR";

    /**
     * 当前审批人下标
     */
    public static final String AUDITOR_IDX_KEY = "AUDITOR_IDX";

    /**
     * 是否已审批
     **/
    public static final String APPROVED_KEY = "AUDIT_APPROVED";

    /**
     * 申请类型
     */
    public static final String AUDIT_TYPE_KEY = "AUDIT_TYPE";

    /**
     * 申请状态
     */
    public static final String AUDIT_STATUS_KEY = "AUDIT_STATUS";

    /**
     * 其他参数
     */
    public static final String AUDIT_PARAMS_KEY = "AUDIT_PARAMS";

    private static final String[] INCLUDE_ACTIVITY_TYPE_ARRAY = {"userTask", "startEvent"};

    private static final String ID_KEY = "USERID";

    private static final String NAME_KEY = "USERNAME";

    private static Logger log = LoggerFactory.getLogger(FlowUtil.class);

    /**
     * 是否存在审批人
     */
    public static boolean existAuditor(DelegateExecution execution) {
        return execution.hasVariable(AUDITOR_KEY);
    }

    /**
     * 获取当前审批人
     */
    public static Optional<CandidateParam> getCandidateFromExecution(DelegateExecution execution) {
        log.info(execution.getVariable(AUDITOR_KEY).toString());
//        String variable = (String) execution.getVariable(AUDITOR_KEY);
        Optional<CandidateParam> candidateParam = Optional.ofNullable(JSONObject.parseObject(JSONObject.toJSONString(
                execution.getVariable(AUDITOR_KEY)), CandidateParam.class));
        return candidateParam;
    }

    public static List<CandidateParam> getCandidateList(DelegateExecution execution) {
        return JSON.parseArray((String) execution.getVariable(AUDITOR_LIST_KEY), CandidateParam.class);
    }

    public static void setCandidate(DelegateExecution execution, CandidateParam param) {
        execution.setVariable(AUDITOR_KEY, param);
    }

    public static boolean isApproved(DelegateExecution execution) {
        Boolean approved = execution.getVariable(APPROVED_KEY, Boolean.class);
        return approved != null && approved;
    }

    public static void end(DelegateExecution execution, AuditStatus status) {
        execution.setVariable(AUDIT_STATUS_KEY, status.toString());
    }

    public static Optional<CandidateParam> getCandidate(Map<String, Object> params) {
//        return Optional.ofNullable(JSONObject.toJavaObject(JSONObject.parseObject(
//                (String) params.get(AUDITOR_KEY)), CandidateParam.class));
        return Optional.ofNullable(JSONObject.parseObject(JSONObject.toJSONString(
                params.get(AUDITOR_KEY)), CandidateParam.class));
    }

    public static String getFromUserName(Map<String, Object> params) {
        return (String) params.get(NAME_KEY);
    }

    public static String getFromUserId(Map<String, Object> params) {
        return (String) params.get(ID_KEY);
    }

    public static void removeCandidate(DelegateExecution execution) {
        execution.removeVariable(AUDITOR_KEY);
    }

    public static Map<String, Object> initStartMap(ProcessParam param) {
        Map<String, Object> map = new HashMap<>();
        // 放入申请类型
        map.put(AUDIT_TYPE_KEY, param.getType());
        // 审批人列表
        map.put(AUDITOR_LIST_KEY, JSONObject.toJSONString(param.getAuditors()));
        // 其他参数
        map.put(AUDIT_PARAMS_KEY, JSONObject.toJSONString(param.getParams()));
        // 审批状态
        map.put(AUDIT_STATUS_KEY, AuditStatus.WAIT_AUDIT.toString());
        return map;
    }

}
