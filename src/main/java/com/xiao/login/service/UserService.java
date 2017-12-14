package com.xiao.login.service;

import com.xiao.login.entity.User;

import java.util.Map;
import java.util.Set;

public interface UserService {

    /**
     * 创建用户
     */
    public User createUser(User user);

    /**
     * 更新user
     */
    public User updateUser(User user);

    /**
     * 修改密码
     */
    public void changePassword(Integer uid, String newPassword);

    /**
     * 添加用户-角色关系
     */
    public void correlationRoles(Integer userId, Integer... roleIds);

    /**
     * 移除用户-角色关系
     */
    public void uncorrelationRoles(Integer userId, Integer... roleIds);

    /**
     * 根据用户名查找用户

     */
    public User findByNickname(String nickname);



    /**
     * 根据用户名查找其角色和权限
     * @return  角色和权限的map集合
     */
    public Map<String,Set<String>> findRolesAndPermission(String nickname);

    /**
     * 根据用户名查找其权限名Set
     */
    public Set<String> findPermissions(String nickname);
}
