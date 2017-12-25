package com.xiao.login.service.impl;


import com.xiao.login.dao.RoleMapper;
import com.xiao.login.dao.UserMapper;
import com.xiao.login.dao.UserRoleMapper;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.entity.UserRole;
import com.xiao.login.service.RoleService;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author Administrator
 * @create 2017-12-13 15:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public void createUser(User user) {
        userMapper.saveUser(user);
    }


    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User getUser(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getAllUsers();
    }


    @Override
    public void correlationRoles(Integer userId, Integer... roleIds) {
        if (userId == null || roleIds.length == 0) {
            return;
        }
        for (Integer roleId : roleIds) {
            if (!exists(userId, roleId)) {
                userRoleMapper.insertUserRole(new UserRole(userId,roleId));
            }
        }
    }

    @Override
    public void uncorrelationRoles(Integer userId, Integer... roleIds) {
        if (userId == null || roleIds.length == 0) {
            return;
        }
        for (Integer roleId : roleIds) {
            if (exists(userId, roleId)) {
                userRoleMapper.deleteUserRole(new UserRole(userId,roleId));
            }
        }
    }

    @Override
    public List<String> findRoles(String userName) {
        return userMapper.getRolesByUserName(userName);
    }

    @Override
    public List<String> findPermissions(String userName) {
        return userMapper.getPermissions(userName);
    }

    @Override
    public List<Role> findRoles(Integer userId) {
        return userMapper.getRolesByUserId(userId);
    }


    private boolean exists(Integer userId, Integer roleId) {
        return userRoleMapper.existUserRole(new UserRole(userId, roleId)) > 0;
    }
}
