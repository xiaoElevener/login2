package com.xiao.login.repository;

import com.xiao.login.entity.RolePermission;
import com.xiao.login.entity.cons.RolePermissionPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @create 2017-12-13 12:57
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,RolePermissionPK>{
}
