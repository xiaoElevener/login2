package com.xiao.login.service;

import com.xiao.login.entity.User;

import java.util.Set;

public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);


    /**
     * 修改密码
     * @param uid
     * @param newPassword
     */
    public void changePassword(Integer uid, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Integer userId, Integer... roleIds);

    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Integer userId, Integer... roleIds);

    /**
     * 根据用户名查找用户
     * @param nickname
     * @return
     */
    public User findByNickname(String nickname);



    /**
     * 根据用户名查找其角色
     * @param nickname
     * @return  角色名的Set集合
     */
    public Set<String> findRoles(String nickname);

    /**
     * 根据用户名查找其权限
     * @param nickname
     * @return
     */
    public Set<String> findPermissions(String nickname);
}
