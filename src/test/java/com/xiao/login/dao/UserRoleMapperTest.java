package com.xiao.login.dao;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.System.out;
import static org.junit.Assert.*;


public class UserRoleMapperTest extends TestBase{

    @Autowired
    private UserRoleMapper mapper;

    @Test
    public void deleteUserRole() throws Exception {
        mapper.deleteUserRole(new UserRole(1,1));

    }

    @Test
    public void insertUserRole() throws Exception {
        mapper.insertUserRole(new UserRole(1,1));
    }

    @Test
    public void existUserRole(){
        out.println(mapper.existUserRole(new UserRole(1,1)));
    }

}