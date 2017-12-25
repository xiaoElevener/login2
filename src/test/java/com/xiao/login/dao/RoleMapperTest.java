package com.xiao.login.dao;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiao_elevener
 * @date 2017-12-19 12:39
 */


public class RoleMapperTest extends TestBase{

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void getRole(){
        Role role= roleMapper.getRole(1);
        Assert.assertNotNull(role);
    }

    @Test
    public void saveRole(){
        Role role = new Role(3,"开发");
        roleMapper.saveRole(role);
    }

    @Test
    public void updateRole(){
        Role role = new Role(1,"测试");
        roleMapper.updateRole(role);

    }

    @Test
    public void deleteRole(){
        roleMapper.deleteRole(3);
    }


}
