package com.xiao.login.repository;

import com.xiao.login.entity.RolePermission;
import com.xiao.login.entity.cons.RolePermissionPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * @author Administrator
 * @create 2017-12-13 12:57
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,RolePermissionPK>{

    /**
     * 通过rid查询RolePermission集合
     * @param rid         角色id
     * @return         RolePermission集合
     */
    public Set<RolePermission> findByRid(Integer rid);


}
