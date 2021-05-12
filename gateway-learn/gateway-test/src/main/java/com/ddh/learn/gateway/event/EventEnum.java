package com.ddh.learn.gateway.event;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/10 19:32
 * @description:
 */
public enum EventEnum {
    DEFAULT(0, "default"),
    ON_BEFORE_UPLOAD(1, "upload"),
    ON_AFTER_DOWNLOAD(2, "download");


    Integer code;
    String desc;

    EventEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static EventEnum getByDesc(String desc) {
        EventEnum[] values = EventEnum.values();
        for (EventEnum value : values) {
            if (desc.equals(value.getDesc())) {
                return value;
            }
        }
        return DEFAULT;
    }
}
