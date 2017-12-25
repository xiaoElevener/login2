package com.xiao.login.dao;

import com.xiao.login.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {

    Integer saveRole(Role role);


    void deleteRole(Integer roleId);


    void updateRole(Role role);


    Role getRole(Integer roleId);


}
