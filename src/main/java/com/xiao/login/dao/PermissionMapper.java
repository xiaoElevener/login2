package com.xiao.login.dao;

import com.xiao.login.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionMapper {

    void savePermission(Permission permission);


    void deletePermission(Integer permissionId);


    void updatePermission(Permission permission);


    Permission getPermission(Integer permissionId);

}
