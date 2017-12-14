package com.xiao.login.repository;

import com.xiao.login.entity.RolePermission;
import com.xiao.login.entity.cons.RolePermissionPK;
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
public class RolePermissionRepositoryTest {

    @Autowired
    private RolePermissionRepository repository;

    @Test
    public void saveOne() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPid(1);
        rolePermission.setRid(2);
        RolePermission result = repository.save(rolePermission);

        assertNotNull(result);
    }

    @Test
    public void findOne() {
        RolePermissionPK rolePermissionPK = new RolePermissionPK();
        rolePermissionPK.setPid(1);
        rolePermissionPK.setRid(2);
        RolePermission result = repository.findOne(rolePermissionPK);
        log.info("【result】,result={}", result);
        assertNotNull(result);
    }


}