package com.xiao.login.service.impl;

import com.xiao.login.dao.PermissionMapper;
import com.xiao.login.entity.Permission;
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
    private PermissionMapper mapper;

    @Override
    public void createPermission(Permission permission) {
        mapper.savePermission(permission);
    }

    @Override
    public void deletePermission(Integer permissionId) {
        mapper.deletePermission(permissionId);
    }
}
