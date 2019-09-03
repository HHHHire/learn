package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"msg"})
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "hello";
    }

    @RequestMapping("/testSession")
    public String testSession(Model model){
        // 底层添加到request域中
        model.addAttribute("msg", "hello");
        return "hello";
    }
}
