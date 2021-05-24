package book.decorator.first;

/**
 * @author: dengdh@dist.com.cn
 * @date: 2021/5/17 13:43
 * @description:
 */
public enum  SizeEnum {
    TALL(0, "小杯"),
    MEDIUM(1, "中杯"),
    LARGE(2, "大杯")
    ;
    private int code;
    private String desc;

    SizeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SizeEnum getByCode(int code) {
        SizeEnum[] values = values();
        for (SizeEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        // 默认中杯
        return SizeEnum.MEDIUM;
    }
}
