package com.ren.springboot.common;

public enum  CommonStatus implements CommonStatusInterface{
    SUCCESS(200, "成功"),
    PARAM_ERROR(400, "参数错误"),
    NO_AUTHORITY(500, "没有权限"),
    PASSWORD_ERROR(501, "密码错误");

    ;

    private int code;
    private String text;


    CommonStatus(int value, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
