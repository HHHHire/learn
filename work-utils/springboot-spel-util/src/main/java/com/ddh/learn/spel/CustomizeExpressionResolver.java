package com.ddh.learn.spel;

/**
 * 定制解析
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/16 16:41
 */
public class CustomizeExpressionResolver extends AbstractExpressionResolver {
    @Override
    public String resolvePlaceholder(String placeholder) {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
