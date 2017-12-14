package com.xiao.login.Enum;

public enum ResultEnum {
    SUCCESS(0, "登录成功"),
    UNKNOWN_ACCOUNT(1,"用户名密码错误"),
    INCORRECT_CREDENTIALS(2,"用户名密码错误"),
    LOCKED_ACCOUNT(3, "账号被锁定了"),

    ;

    final public Integer code;
    final public String message;

    private ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
