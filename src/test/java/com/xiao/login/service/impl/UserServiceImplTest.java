package com.xiao.login.service.impl;

import com.xiao.login.entity.User;
import com.xiao.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        User user = new User();
        user.setNickname("xiao");
        user.setEmail("389855286@qq.com");
        user.setPswd("123456");
        user.setCreateTime(new Date());
        User result = userService.createUser(user);
        log.info("【user】 user={}", user);
        Assert.notNull(result);

    }

    @Test
    public void changePassword() {
        userService.changePassword(16,"654321");
    }

    @Test
    public void correlationRoles() {
        userService.correlationRoles(2,1,2,3,4);
    }

    @Test
    public void uncorrelationRoles() {
        userService.uncorrelationRoles(2, 1, 2, 3, 4);
    }

    @Test
    public void findByNickname() {
        Set<String> set = userService.findRoles("xiao");
        log.info("【通过昵称查询角色集合】 set={}",set);
        Assert.notNull(set);
    }

    @Test
    public void findRoles() {
    }

    @Test
    public void findPermissions() {
    }
}