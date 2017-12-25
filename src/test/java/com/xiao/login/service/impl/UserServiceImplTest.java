package com.xiao.login.service.impl;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.entity.UserRole;
import com.xiao.login.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class UserServiceImplTest extends TestBase{

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User(3,"gouzi","lld@163.com","123456",new Date(),new Date(),1);
        userService.createUser(user);
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User(3,"傻子","lld@163.com",null,null,null,1);
        userService.updateUser(user);
    }

    @Test
    public void deleteUser() throws Exception {
        userService.deleteUser(3);
    }

    @Test
    public void getUser() throws Exception {
        out.println("userService.getUser()的值是:---" + userService.getUser("xiao") + ",当前方法=UserServiceImplTest,getUser()");
    }

    @Test
    public void correlationRoles() throws Exception {
        userService.correlationRoles(2,1,2,3);
    }

    @Test
    public void uncorrelationRoles() throws Exception {
        userService.uncorrelationRoles(2,1,2,3);
    }

    @Test
    public void findRoles() throws Exception {
        List<String> roles= userService.findRoles("xiao");
        out.println(roles);
    }

    @Test
    public void findPermissions() throws Exception {
        List<String> permissions=userService.findPermissions("xiao");
        out.println(permissions);
    }

}