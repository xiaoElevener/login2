package com.xiao.login.Enum;

public enum RoleEnum {

    PM(1,"产品"),
    STE(2,"测试"),
    PG(3,"开发"),
    ;

    final public Integer roleId;
    final public String roleName;

    private RoleEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
