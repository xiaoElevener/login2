package com.xiao.login.Enum;

public enum ResultEnum {
    SUCCESS(0, "登录成功"),
    UNKNOWN_ACCOUNT(1,"用户名密码错误"),
    INCORRECT_CREDENTIALS(2,"用户名密码错误"),
    LOCKED_ACCOUNT(3, "账号被锁定了"),
    PM_SUCCESS(4,"产品经理  角色验证成功"),
    STE_SUCCESS(5,"测试  角色验证成功"),
    PG_SUCCESS(6,"开发  角色验证成功"),
    PERMISSION_FAILED(7,"无权访问"),
    PERMISSION_SUCCESS(8,"有权访问"),
    SYSTEM_ERROR(9,"系统错误"),
    ;

    final public Integer code;
    final public String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
