package cn.edu.jxust.redislogin.service;

import cn.edu.jxust.redislogin.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author: ddh
 * @date: 2019/10/29  10:52
 * @description:
 */
public interface LoginService {

    public String getString(String key);

    public User login(String userName, String password);

    /**
     * 用户在两分钟内允许输错5次，如果超过次数限制登陆1小时
     * @param userName 用户名
     * @return String
     */
    public String loginValdate(String userName);

    /**
     * 判断当前用户是否被限制登陆
     * @param userName 用户名
     * @return String
     */
    public Map<String, Object> loginUserLock(String userName);

    void listQueueInit(String cardId);

    void listQueueTouch(String cardId);

    List<String> listQueueSucc(String cardId);

    List<String> listQueueWait(String cardId);
}
