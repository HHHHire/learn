package com.entity;

/**
 * @author: ddh
 * @date: 2019/10/24  14:55
 * @description:
 */
public class Singer implements ISinger {
    @Override
    public void sing() {
        System.out.println("sing a song");
    }
}
