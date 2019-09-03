package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user")
public class HelloController {
    @RequestMapping("/interceptor")
    public String testInterceptor(){
        System.out.println("hello world");
        return "success";
    }


}
