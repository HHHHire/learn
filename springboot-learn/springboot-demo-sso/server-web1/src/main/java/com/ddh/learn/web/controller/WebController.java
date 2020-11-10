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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/17 15:37
 */
@RestController
@RequestMapping("web1")
public class WebController {

    private final String WEB1 = "web1";
    private final String NAME = "zhangsan";
    private final String PWD = "123456";
    private final String TOKEN = "token";
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @Reference
    private SsoService ssoService;

    @GetMapping("getData")
    public String getData(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "token",required = false) String token) throws ServletException, IOException {
        // 先判断有没有本机 session
        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User)session.getAttribute(WEB1);
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
                request.getSession().setAttribute(WEB1, user);
                return "成功拿到数据";
            }
        }

        // 去 sso 登录
//        try {
//            response.sendRedirect("http://www.sso.com:8083/sso/loginCheck");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        request.getRequestDispatcher("http://www.sso.com:8083/sso/loginCheck").forward(request,response);
        return "error";
    }







    /******************************************************************/

    @GetMapping("test")
    public String test(HttpServletRequest request) {
        return "success";
    }
}
