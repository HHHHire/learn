package com.ddh.learn.sso.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddh.learn.api.model.User;
import com.ddh.learn.api.service.SsoService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/9/17 17:16
 */
@Service
public class SsoServiceImpl implements SsoService {
    private final StringRedisTemplate redisTemplate;

    private final String NAME = "zhangsan";
    private final String PWD = "123456";
    private final String SSO = "sso";
    private final String TOKEN = "token";

    public SsoServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 检查是否有sso的session，如果有就不用登录，返回令牌
     */
    @Override
    public String loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // session 不为空
        if (session != null) {
            User sso = (User) session.getAttribute(SSO);
            if (sso != null && NAME.equals(sso.getName()) && PWD.equals(sso.getPwd())) {
                if (redisTemplate.hasKey(TOKEN)) {
                    return redisTemplate.opsForValue().get(TOKEN);
                } else {
                    String token = UUID.randomUUID().toString();
                    redisTemplate.opsForValue().set(TOKEN, token);
                    request.setAttribute(TOKEN, token);
                    return token;
                }
            }
        }
        return "你需要重新登录";
    }

    /**
     * 登录获取令牌
     */
    @Override
    public String login(String name, String pwd, HttpServletRequest request, HttpServletResponse response) {
        // 首先判断是否登录过
        HttpSession session = request.getSession();
        // session 不为空，且检查正确，返回 token
        if (session != null) {
            User sso = (User) session.getAttribute(SSO);
            if (sso != null && NAME.equals(sso.getName()) && PWD.equals(sso.getPwd())) {
                String token;
                if (redisTemplate.hasKey(TOKEN)) {
                    token = redisTemplate.opsForValue().get(TOKEN);
                } else {
                    token = UUID.randomUUID().toString();
                    redisTemplate.opsForValue().set(TOKEN, token);
                    return token;
                }
            }
        }

        // 登录成功生成 sso 的 session 和令牌
        if (name != null && pwd != null) {
            if (NAME.equals(name) && PWD.equals(pwd)) {
                User user = User.builder().name(name).pwd(pwd).build();
                request.getSession().setAttribute(SSO, user);
                // token
                String token = UUID.randomUUID().toString();
                // 存入 redis 中，5 分钟过期
                redisTemplate.opsForValue().set(TOKEN, token);
                return token;
            }
        }
        return "登录失败";
    }

    @Override
    public boolean checkToken(String token) {
        String check;
        if ((check = redisTemplate.opsForValue().get(TOKEN)) != null && check.equals(token)) {
            return true;
        }
        return false;
    }

    @Override
    public String logout(HttpServletRequest request) {
        if (redisTemplate.opsForValue().get(SSO) != null) {
            redisTemplate.delete(SSO);
        }
        request.getSession().removeAttribute(SSO);
        return "登出成功";
    }
}
