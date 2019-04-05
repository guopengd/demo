package com.example.demo.entity;


/**
 * <p>
 * 全局异常回复实体类
 * </p>
 *
 * @author gpd
 * @date 2019/3/29
 */
public class ErrorResponseEntity {

    /**
     * 错误code码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}