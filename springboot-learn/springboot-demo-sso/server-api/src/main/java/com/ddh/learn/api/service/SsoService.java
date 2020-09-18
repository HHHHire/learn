package com.ddh.learn.api.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/17 17:17
 */
public interface SsoService {
//    String loginCheck(HttpServletRequest request);

    /**
     * 登录获取令牌
     */
    String login(String name, String pwd, HttpServletRequest request);

    boolean checkToken(String token);

    String logout(HttpServletRequest request);
}
