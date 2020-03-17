package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Token;

/**
 * @author: ddh
 * @data: 2020/1/5 21:13
 * @description
 **/
public interface TokenSerivce {
    Token getTokenById(String userId);

    Token createToken(Token token);

    Integer deleteTokenByUserId(String userId);

    boolean isExist(String token);
}
