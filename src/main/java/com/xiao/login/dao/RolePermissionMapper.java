package com.xiao.login.dao;

import com.xiao.login.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface RolePermissionMapper {

    void deleteRolePermission(RolePermission rolePermission);

    void saveRolePermission(RolePermission rolePermission);

    Integer existRolePermission(RolePermission rolePermission);
}
