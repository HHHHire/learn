package cn.edu.jxust.springbootmybatisdemo.controller;

import cn.edu.jxust.springbootmybatisdemo.entity.User;
import cn.edu.jxust.springbootmybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddh
 * @date: 2019/10/31  22:44
 * @description:
 */
@RestController
@RequestMapping("/login")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("getName/{id}")
    public User getName(@PathVariable("id") String id) {
        return userService.getName(id);
    }
}
