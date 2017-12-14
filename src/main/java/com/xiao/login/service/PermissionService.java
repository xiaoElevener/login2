package com.xiao.login.service;

import com.xiao.login.entity.Permission;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.RolePermission;

public interface PermissionService {

    public Permission createPermission(Permission permission);
    public void deletePermission(Integer permissionId);

}
