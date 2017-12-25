package com.xiao.login.service.impl;


import com.xiao.login.dao.RoleMapper;
import com.xiao.login.dao.RolePermissionMapper;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.RolePermission;
import com.xiao.login.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author Administrator
 * @create 2017-12-14 12:29
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void createRole(Role role) {
        roleMapper.saveRole(role);
    }

    @Override
    public Role findRole(Integer roleId) {
        return roleMapper.getRole(roleId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Integer roleId, Integer... permissionIds) {
        if(roleId == null){
            return;
        }

        for (Integer permissionId:
             permissionIds) {
            if(!exist(roleId,permissionId)){
                rolePermissionMapper.saveRolePermission(new RolePermission(roleId, permissionId));
            }
        }


    }

    @Override
    public void uncorrelationPermissions(Integer roleId, Integer... permissionIds) {
        if(roleId == null){
            return;
        }

        for (Integer permissionId:
                permissionIds) {
            if(exist(roleId,permissionId)){
                rolePermissionMapper.deleteRolePermission(new RolePermission(roleId, permissionId));
            }
        }
    }


    private boolean exist(Integer roleId,Integer permissionId){
        return rolePermissionMapper.existRolePermission(new RolePermission(roleId,permissionId))>0;
    }
}
