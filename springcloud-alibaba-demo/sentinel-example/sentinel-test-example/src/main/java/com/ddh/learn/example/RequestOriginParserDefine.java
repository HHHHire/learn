package com.ddh.learn.example;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * sentinel 系统规则
 * @author: dengdh@dist.com.cn
 * @data: 2020/8/6 13:16
 */
@Component
public class RequestOriginParserDefine implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serviceName");
    }
}
