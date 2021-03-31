package com.ddh.learn.utils.controller;

import com.ddh.learn.utils.model.request.UserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/31 12:13
 */
@RestController
public class TestControllerV2 {
    @GetMapping("/test/v2")
    public Object getUser(HttpServletResponse response) throws IOException {
        UserRequest build = UserRequest.builder().id("test").name("zhangsan").build();
        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(build.toString());
        return build;
    }
}
