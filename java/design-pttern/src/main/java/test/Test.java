package test;

import singleton.hungry.SingletonHungry;
import singleton.lazy.SingletonLazy;

/**
 * @author ddh
 * @date 2019/8/12 21:24
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(SingletonLazy.getInstance());
        System.out.println(SingletonLazy.getInstance());
        System.out.println(SingletonLazy.getInstance());
        System.out.println(SingletonLazy.getInstance());
    }
}
