package com.java.collection.iterator;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author: ddh
 * @date: 2019/10/22  19:21
 * @description:
 */
public class IteratortTest1 {
    public static void main(String[] args) {
        List<String> c = new ArrayList<>();
        c.add("aaa");
        c.add("bbb");
        c.add("ccc");
        /*Object[] objects = c.toArray();
        for (Object object : objects) {
            System.out.println(object.toString());
        }*/

//        for (Iterator i = c.iterator(); i.hasNext();) {
//            Object next = i.next();
//            System.out.println(next);
//        }

//        for (String next : c) {
//            System.out.println(next);
//        }

        Integer i = new Integer(1);
        boolean equals = i.equals(1);
        System.out.println(equals);
        System.out.println(i == 1);

    }
}
