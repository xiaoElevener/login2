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
    public Role createRole(Role role);

    /**
     * 通过角色id查找role
     * @param rid  角色id
     * @return    角色对象
     */
    public Role findRole(Integer rid);

    /**
     * 删除角色
     *
     * @param rid 角色id
     */
    public void deleteRole(Integer rid);

    /**
     * 添加角色-权限联系
     *
     * @param rid         角色id
     * @param permissionIds 权限id数组
     * @return
     */
    public void correlationPermissions(Integer rid, Integer... permissionIds);


    /**
     * 删除角色-权限联系
     *
     * @param rid         角色id
     * @param permissionIds 权限id数组
     * @return
     */
    public void uncorrelationPermissions(Integer rid, Integer... permissionIds);

    public Set<String> findPermissions(Set<Integer> roleIdSet);

}
