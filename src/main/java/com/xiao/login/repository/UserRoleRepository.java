package com.xiao.login.repository;

import com.xiao.login.entity.User;
import com.xiao.login.entity.UserRole;
import com.xiao.login.entity.cons.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
    /**
     * 通过uid查询UserRoles  Set集合
     * @param uid
     * @return
     */
    public Set<UserRole> findByUid(Integer uid);
}
