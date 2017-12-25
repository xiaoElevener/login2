package com.xiao.login.dao;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.RolePermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


public class RolePermissionMapperTest extends TestBase {

    @Autowired
    private RolePermissionMapper mapper;

    @Test
    public void deleteRolePermission() throws Exception {
        mapper.deleteRolePermission(new RolePermission(1,1));
    }

    @Test
    public void saveRolePermission() throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(1);
        rolePermission.setRoleId(1);
        mapper.saveRolePermission(rolePermission);

    }


}