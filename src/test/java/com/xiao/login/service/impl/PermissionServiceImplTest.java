package com.xiao.login.service.impl;

import com.xiao.login.base.TestBase;
import com.xiao.login.dao.PermissionMapper;
import com.xiao.login.entity.Permission;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PermissionServiceImplTest extends TestBase{

    @Autowired
    private PermissionMapper mapper;

    @Test
    public void createPermission() throws Exception {
        mapper.savePermission(new Permission(4,"删除"));

    }

    @Test
    public void deletePermission() throws Exception {
        mapper.deletePermission(4);

    }

    @Test
    public void updatePermissin() throws Exception{
        mapper.updatePermission(new Permission(4,"流弊"));
    }

    @Test
    public void getPermission() throws Exception{
        mapper.getPermission(4);
    }

}