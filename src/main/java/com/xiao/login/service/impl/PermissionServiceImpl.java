package com.xiao.login.service.impl;

import com.xiao.login.entity.Permission;
import com.xiao.login.repository.PermissionRepository;
import com.xiao.login.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @create 2017-12-14 13:54
 */
@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Integer permissionId) {
        permissionRepository.delete(permissionId);
    }
}
