package com.xiao.login.dao;

import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户实体
     * @return userId
     */
    Integer saveUser(User user);

    /**
     * 删除用户
     * @param userId 用户id
     * @return 影响行数
     */
    Integer deleteUser(Integer userId);

    /**
     * 修改用户(此处不更新密码)
     * @return 影响行数
     */
    Integer updateUser(User user);

    /**
     * 获取所有用户信息
     * @return 用户信息集合
     */
    List<User> getAllUsers();

    /**
     * 查询用户
     * @param userId 用户id
     * @return 用户
     */
    User getUser(Integer userId);


    User getUserByUserName(String userName);


    List<String> getRolesByUserName(String userName);

    List<Role> getRolesByUserId(Integer userId);

    List<String> getPermissions(String userName);
}
