package com.ddh.learn.spel;

import org.springframework.core.Ordered;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/16 16:12
 */
public interface ExpressionResolver<T> extends Ordered {
    /**
     * 替换表达式
     *
     * @param placeholder 表达式
     * @return 替换表达式之后的结果
     */
    T evaluate(String placeholder);

    /**
     * 测试替换
     *
     * @param placeholder
     * @return
     */
    default T testEvaluate(String placeholder) {
        return this.evaluate(placeholder);
    }

    /**
     * 获取表达式前缀
     *
     * @return
     */
    String getPlaceholderPrefix();

    /**
     * 获取表达式后缀
     *
     * @return
     */
    String getPlaceholderSuffix();

}
