package com.ddh.learn.java.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 16:06
 */
public class Function1 {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>(10);
        List<String> list;

        // 之前的写法
        /*list = map.get("key");
        if (list == null) {
            list = new ArrayList<>();
            map.put("key", list);
        }
        list.add("hello world");*/

        // function
        list = map.computeIfAbsent("key", k->new ArrayList<>());
        list.add("hello world");
        System.out.println(map);
    }

}
