package com.xiao.login.repository;

import com.xiao.login.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @create 2017-12-13 11:15
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{
     Role findById(Integer id);
}
