package com.ddh.practicedemo.util;

import java.util.UUID;

/**
 * @author ddh
 * @date 2019/8/2 15:20
 * @description 生成UUID
 **/
public class UUIDUtil {
    /**
     * 获取UUID
     *
     * @return String
     */
    public static String getUUID(){
        return UUID.randomUUID()
                .toString()
                .replace("-","");
    }
}
