package com.xiao.login.service;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RoleServiceTest extends TestBase{

    @Autowired
    private RoleService roleService;

    @Test
    public void createRole() throws Exception {
        roleService.createRole(new Role(4, "兼职"));

    }

    @Test
    public void findRole() throws Exception {
        roleService.findRole(4);
    }

    @Test
    public void deleteRole() throws Exception {
        roleService.deleteRole(4);

    }

    @Test
    public void correlationPermissions() throws Exception {
        roleService.correlationPermissions(2,1,2,3);
    }

    @Test
    public void uncorrelationPermissions() throws Exception {
        roleService.uncorrelationPermissions(2,1,2,3);
    }

}