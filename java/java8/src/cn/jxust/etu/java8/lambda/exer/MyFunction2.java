package cn.jxust.etu.java8.lambda.exer;

/**
 * @author: ddh
 * @date: 2019/9/19  8:59
 * @description:
 */
@FunctionalInterface
public interface MyFunction2<T, R> {
    public R getValue(T t1, T t2);
}
