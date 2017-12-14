package com.xiao.login.service.impl;

import com.xiao.login.entity.Role;
import com.xiao.login.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void createRole() {
        Role role = new Role();
        role.setId(4);
        role.setName("管理员");
        role.setType("admin");
        Role result = roleService.createRole(role);
        log.info("【创建角色】role={}",result);
        assertNotNull(result);
    }

    @Test
    public void deleteRole() {
        roleService.deleteRole(4);
    }

    @Test
    public void correlationPermissions() {
        roleService.correlationPermissions(1,1,2);
    }

    @Test
    public void uncorrelationPermissions() {
        roleService.uncorrelationPermissions(1,1,2);
    }


    @Test
    public void findPermissions() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        Set<String> permissions = roleService.findPermissions(set);
        log.info("【permissions】{}", permissions);

    }
}