package com.ddh.learn.netty.rpc;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 10:05
 * @description:
 */
public class UserServiceImpl implements UserService {

    @Override
    public String hello(String word) {
        System.out.println("收到客户端消息:" + word);
        return "收到客户端消息:" + word;
    }
}
