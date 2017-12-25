package com.xiao.login.dao;

import com.xiao.login.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface UserRoleMapper {

    void deleteUserRole(UserRole userRole);

    void insertUserRole(UserRole userRole);

    Integer existUserRole(UserRole userRole);
}
