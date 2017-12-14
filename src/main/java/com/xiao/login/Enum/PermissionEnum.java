package com.xiao.login.Enum;

public enum PermissionEnum {
    VIEW(1,"查看"),
    CREATE(2,"创建"),
    DELETE(3,"删除"),
    ;
    final public Integer permissionId;
    final public String permissionName;

    private PermissionEnum(Integer permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }
}
