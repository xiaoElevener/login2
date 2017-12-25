package com.xiao.login.service;

import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    void createUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 获取用户信息
     * @param userName 用户名
     * @return
     */
    User getUser(String userName);

    List<User> getUsers();

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Integer userId, Integer... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Integer userId, Integer... roleIds);


    /**
     * 查询用户拥有的角色
     * @param userName
     * @return
     */
    List<String> findRoles(String userName);

    /**
     * 根据用户名查找其权限名Set
     */
    List<String> findPermissions(String userName);

    /**
     * 通过userId查找对应权限
     * @param userId
     * @return
     */
    List<Role> findRoles(Integer userId);
}
