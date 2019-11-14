package cn.edu.jxust.redislogin.controller;

import cn.edu.jxust.redislogin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: ddh
 * @date: 2019/10/29  10:51
 * @description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password) {
        // 判断当前用户是否被限制登陆
        Map<String, Object> map = loginService.loginUserLock(userName);
        if ((boolean)map.get("flag")){
            return "登陆失败" + userName + "超过登陆次数，还剩:" + map.get("time") + "分钟";
        } else {
            loginService.login(userName, password);
        }
        return null;
    }
}
