package com.ddh.learn.spel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2021/4/16 16:30
 */
public abstract class AbstractExpressionResolver implements StringExpressionResolver {
    /**
     * 表达式前缀
     */
    private String placeholderPrefix;
    /**
     * 表达式后缀
     */
    private String placeholderSuffix;
    /**
     * 值分隔符
     */
    private String valueSeparator = null;
    /**
     * 已知简单表达式前缀
     */
    private static final Map<String, String> WELLKNOWN_SIMPLE_PREFIXES = new HashMap<>(4);
    /**
     * 简单表达式前缀
     */
    private String simplePrefix;

    static {
        WELLKNOWN_SIMPLE_PREFIXES.put("{", "}");
        WELLKNOWN_SIMPLE_PREFIXES.put("[", "]");
        WELLKNOWN_SIMPLE_PREFIXES.put("(", ")");
    }

    @Override
    public String evaluate(String placeholder) {
        return null;
    }

    /**
     * 解析表达式的值
     * @return
     */
    public abstract String resolvePlaceholder(String placeholder);

    public void setPlaceholderPrefix(String placeholderPrefix) {
        this.placeholderPrefix = placeholderPrefix;
        if (WELLKNOWN_SIMPLE_PREFIXES.containsKey(placeholderPrefix)) {
            this.simplePrefix = placeholderPrefix;
        }
    }

    public void setPlaceholderSuffix(String placeholderSuffix) {
        this.placeholderSuffix = placeholderSuffix;
    }

    public void setValueSeparator(String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }

    @Override
    public String getPlaceholderPrefix() {
        return placeholderPrefix;
    }

    @Override
    public String getPlaceholderSuffix() {
        return placeholderSuffix;
    }
}
