package test;

import singleton.hungry.SingletonHungry;

/**
 * @author ddh
 * @date 2019/8/12 21:24
 * @description
 **/
public class Test {
    public static void main(String[] args) {
        System.out.println(SingletonHungry.getInstance());
        System.out.println(SingletonHungry.getInstance());
        System.out.println(SingletonHungry.getInstance());
        System.out.println(SingletonHungry.getInstance());
    }
}
