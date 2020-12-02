package com.ddh.learn.demo.utils;

import lombok.Data;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/15 18:15
 */
@Data
public class BizException extends RuntimeException{
    private static final long serialVersionUID = 6889615150063521500L;

    public Integer code;

    public BizException(){super();}

    public BizException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public BizException(ApiResult errorInfo) {
        this(errorInfo.getCode(), errorInfo.getMsg());
    }
}
