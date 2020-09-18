package com.ddh.learn.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddh.learn.api.model.User;
import com.ddh.learn.api.service.SsoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/18 10:34
 */
@RestController
@RequestMapping("web2")
public class WebController {
    private final String WEB2 = "web2";
    private final String NAME = "zhangsan";
    private final String PWD = "123456";
    private final String TOKEN = "token";
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @Reference
    private SsoService ssoService;

    @GetMapping("getData")
    public String getData(HttpServletRequest request, @RequestParam(value = "token",required = false) String token){
        // 先判断有没有本机 session
        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User)session.getAttribute(WEB2);
            if (user != null && NAME.equals(user.getName()) && PWD.equals(user.getPwd())) {
                logger.info("session 校验成功");
                return "成功拿到数据";
            }
        }

        // 接下来在根据令牌判断
        if (token != null) {
            // 去 sso 校验
            if (ssoService.checkToken(token)) {
                logger.info("token 校验成功");
                User user = User.builder().name(NAME).pwd(PWD).build();
                request.getSession().setAttribute(WEB2, user);
                return "成功拿到数据";
            }
        }

        // 去 sso 登录
        return "请去登录: www.sso.com:8083/sso/login?name=zhangsan&pwd=123456";
    }
}
