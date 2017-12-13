package com.xiao.login.repository;

import com.xiao.login.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer>{
}
