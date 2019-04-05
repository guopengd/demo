package com.example.demo.utilty;

/**
 * <p>
 * 切面通知枚举类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public enum AspectCode {
    Before(0, "前置通知"),
    AfterThrowing(1, "异常通知");

    private Integer code;
    private String desc;

    private AspectCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AspectCode getEnum(Integer code) {
        AspectCode[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            AspectCode aspectCode = var1[var3];
            if (aspectCode.getCode().equals(code)) {
                return aspectCode;
            }
        }

        return null;
    }
}
