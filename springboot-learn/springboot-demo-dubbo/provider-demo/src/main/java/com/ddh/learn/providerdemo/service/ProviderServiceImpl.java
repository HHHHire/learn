package com.ddh.learn.providerdemo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddh.learn.api.service.ProviderService;
import com.ddh.learn.providerdemo.util.RedisPoolUtil;
import com.ddh.learn.providerdemo.util.network.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.Inet4Address;
import java.net.SocketException;

/**
 * @author: ddh
 * @data: 2020/6/24 9:53
 * @description
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private RedisPoolUtil redisPoolUtil;

    @Override
    public String say() {

        int visitCounts = 1;

        String localAddr = "";
        try {
            Inet4Address inet4Address = NetworkUtil.getIpBySocket().orElse(null);
            if (inet4Address != null) {
                localAddr = inet4Address.getHostAddress();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        byte[] valueByte = redisPoolUtil.getValueByte(localAddr.getBytes());
        if (valueByte != null && valueByte.length != 0) {
            String string = new String(valueByte);
            visitCounts = Integer.valueOf(string) + 1;
            redisPoolUtil.setValueByte(localAddr.getBytes(), Integer.toString(visitCounts).getBytes());
        } else {
            redisPoolUtil.setValueByte(localAddr.getBytes(), Integer.toString(visitCounts).getBytes());
        }
        return "hello world! v4  </br>" +
                "IP: " + localAddr + "</br>" +
                "you have visit:  " + visitCounts + "   times!";


    }
}
