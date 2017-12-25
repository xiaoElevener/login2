package com.xiao.login.dao;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class PermissionMapperTest extends TestBase{

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void savePermission() throws Exception {
        Permission permission = new Permission();
        permission.setPermissionId(4);
        permission.setPermissionName("范德萨");
        permissionMapper.savePermission(permission);
    }

    @Test
    public void deletePermission() throws Exception {
        permissionMapper.deletePermission(1);
    }

    @Test
    public void updatePermission() throws Exception {
        permissionMapper.updatePermission(new Permission(1,"的撒"));
    }

    @Test
    public void getPermission() throws Exception {
        out.println(permissionMapper.getPermission(1));
    }

}