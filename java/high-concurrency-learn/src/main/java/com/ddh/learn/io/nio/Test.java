package com.ddh.learn.io.nio;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/10/9 16:03
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(3 << 3);
//        FileInputStream fileInputStream = new FileInputStream(new File(""));
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        FileReader fileReader = new FileReader("");

        Test test = new Test();
        System.out.println(test.hashCode());

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());

    }
}
