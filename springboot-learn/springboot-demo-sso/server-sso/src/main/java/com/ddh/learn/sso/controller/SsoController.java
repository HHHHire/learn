package com.ddh.learn.sso.controller;

import com.ddh.learn.api.service.SsoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/17 15:17
 */
@RestController
@RequestMapping("sso")
public class SsoController {
    private final SsoService ssoService;

    private final String SSO = "sso";

    public SsoController(SsoService ssoService) {
        this.ssoService = ssoService;
    }

    /**
     * 检查是否有sso的session，如果有就不用登录，返回令牌
     */
    @GetMapping("loginCheck")
    public String loginCheck(HttpServletRequest request, HttpServletResponse response) {
        return ssoService.loginCheck(request);
    }

    /**
     * 登录获取令牌
     */
    @GetMapping("login")
    public String login(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "pwd", required = false) String pwd,
                        HttpServletRequest request, HttpServletResponse response) {
        return ssoService.login(name, pwd, request, response);
    }

    @GetMapping("checkToken/{token}")
    public boolean checkToken(@PathVariable("token") String token) {
        return ssoService.checkToken(token);
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        return ssoService.logout(request);
    }


    /***************************************************************************************/

    @GetMapping("test")
    public String test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("hello", "world");
        return "success";
    }

    @GetMapping("test2")
    public String test2(HttpServletRequest request) {
        Object sso = request.getSession().getAttribute(SSO);
        return sso.toString();
    }
}
