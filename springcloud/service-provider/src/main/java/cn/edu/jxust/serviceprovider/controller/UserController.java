package cn.edu.jxust.serviceprovider.controller;

import cn.edu.jxust.serviceprovider.pojo.User;
import cn.edu.jxust.serviceprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ddh
 * @Date: 2019/9/30 16:35
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User queryUserById(@PathVariable("id") Long id) {
        System.out.println("hello");
        return this.userService.queryUserById(id);
    }
}
