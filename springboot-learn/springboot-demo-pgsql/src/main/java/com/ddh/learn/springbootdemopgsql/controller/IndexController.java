package com.ddh.learn.springbootdemopgsql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/31 16:20
 * @description:
 */
@Controller
public class IndexController {
    @GetMapping("/index")
    public String getPage() {
        return "index";
    }
}
