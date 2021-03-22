package com.ddh.learn.utils.controller;

import com.ddh.learn.utils.security.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/3/22 9:43
 */
@Controller
public class VerifyCodeController {
    @GetMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        VerifyCode instance = VerifyCode.Instance();
        try {
            ImageIO.write(instance.getImage(), "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
