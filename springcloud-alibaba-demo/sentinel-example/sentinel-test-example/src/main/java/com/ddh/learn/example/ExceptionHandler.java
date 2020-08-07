package com.ddh.learn.example;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * sentinel的异常
 *
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 13:43
 */
public class ExceptionHandler implements UrlBlockHandler {

    /**
     * BlockException 异常接口，包含sentinel五种异常
     * FlowException 限流异常
     * DegradeException 降级异常
     * ParamFlowException 参数限流异常
     * AuthorityException 授权异常
     * SystemBlockException 系统负载异常
     */
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseData data = null;
        if (e instanceof FlowException) {
            data = new ResponseData(-1, "限流异常");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(-2, "降级异常");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(data));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class ResponseData {
        private Integer code;
        private String msg;
    }
}
