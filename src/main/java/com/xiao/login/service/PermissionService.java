package com.xiao.login.service;

import com.xiao.login.entity.Permission;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.RolePermission;

public interface PermissionService {

   void createPermission(Permission permission);

   void deletePermission(Integer permissionId);

}
