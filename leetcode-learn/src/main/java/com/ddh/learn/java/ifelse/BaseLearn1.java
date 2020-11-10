package com.ddh.learn.java.ifelse;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 14:10
 */
public class BaseLearn1 {

    public static void main(String[] args) {
        String info = "";
        String info1 = null;
        String info2 = "hello world";
        String info3 = " ";
        List<String> strings = new ArrayList<>(Arrays.asList(info, info1, info2, info3));
        strings.removeIf(StringUtils::isEmpty);
//        System.out.println(strings.get(0));

        strings.sort(Comparator.comparing(x -> x));
        System.out.println(strings);

        // 可用于代替下面的判断

        /*if (info != null) {
            use info
        }
        if (info1 != null ){
            use info1
        }
        if (info2 != null) {
            use info2
        }*/
    }
}
