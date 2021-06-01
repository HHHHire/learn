package com.ddh.learn.netty.rpc;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/6/1 10:01
 * @description:
 */
public class ClientBootstrap {
    private static String productName = "dubbo:rpc:";

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        UserService proxy = (UserService) nettyClient.getBean(UserService.class, productName);

        String hello = proxy.hello("hello");
        System.out.println(hello);

    }
}
