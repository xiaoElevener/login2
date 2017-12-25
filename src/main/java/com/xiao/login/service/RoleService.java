package com.xiao.login.service;

import com.xiao.login.entity.Role;
import com.xiao.login.entity.RolePermission;

import java.util.Set;

public interface RoleService {

    /**
     * 创建role
     *
     * @param role role实体
     * @return 新建角色
     */
    void createRole(Role role);

    /**
     * 通过角色id查找role
     * @param roleId  角色id
     * @return    角色对象
     */
    Role findRole(Integer roleId);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void deleteRole(Integer roleId);

    /**
     * 添加角色-权限联系
     *
     * @param roleId         角色id
     * @param permissionIds 权限id数组
     */
    void correlationPermissions(Integer roleId, Integer... permissionIds);


    /**
     * 删除角色-权限联系
     *
     * @param roleId         角色id
     * @param permissionIds 权限id数组
     * @return
     */
    void uncorrelationPermissions(Integer roleId, Integer... permissionIds);

}
