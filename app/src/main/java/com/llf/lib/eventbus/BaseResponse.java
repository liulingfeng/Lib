package com.llf.lib.eventbus;

/**
 * Created by llf on 2016/7/28.
 * 基础的bean
 */
public class BaseResponse {
    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
