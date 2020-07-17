package com.ddh.learn.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/7/14 23:55
 * @desc
 */
public class Test1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Collection<String> values = map.values();

    }
}
