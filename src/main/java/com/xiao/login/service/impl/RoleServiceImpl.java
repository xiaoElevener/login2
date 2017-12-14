package com.xiao.login.service.impl;

import com.xiao.login.entity.Permission;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.RolePermission;
import com.xiao.login.entity.cons.RolePermissionPK;
import com.xiao.login.repository.PermissionRepository;
import com.xiao.login.repository.RolePermissionRepository;
import com.xiao.login.repository.RoleRepository;
import com.xiao.login.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @create 2017-12-14 12:29
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRole(Integer rid) {
        return roleRepository.findOne(rid);
    }

    @Override
    public void deleteRole(Integer rid) {
        roleRepository.delete(rid);
    }

    @Override
    public void correlationPermissions(Integer rid, Integer... permissionIds) {
        if (rid == null || permissionIds.length == 0) {
            return;
        }
        for (Integer pid : permissionIds) {
            if (!exists(rid, pid)) {
                rolePermissionRepository.save(new RolePermission(rid, pid));
            }
        }
    }

    @Override
    public void uncorrelationPermissions(Integer rid, Integer... permissionIds) {
        if (rid == null || permissionIds.length == 0) {
            return;
        }
        for (Integer pid : permissionIds) {
            if (exists(rid, pid)) {
                rolePermissionRepository.delete(rolePermissionRepository.findOne(new RolePermissionPK(rid, pid)));
            }
        }
    }

    @Override
    public Set<String> findPermissions(Set<Integer> roleIdSet) {
        //权限名集合
        Set<String> permissionSet = new HashSet<>();

        //权限id集合
        Set<Integer> permissionIdSet = new HashSet<>();

        //通过角色id查询 角色-权限 集合
        for (Integer rid : roleIdSet
                ) {

            Set<RolePermission> set = rolePermissionRepository.findByRid(rid);

            for (RolePermission rp : set
                    ) {
                permissionIdSet.add(rp.getPid());
            }
        }

        log.info("【permissionIdSet】={}",permissionIdSet);

        for (Integer pid : permissionIdSet
                ) {
            permissionSet.add(permissionRepository.findOne(pid).getName());
        }
        return permissionSet;


    }


    public boolean exists(Integer rid, Integer pid) {
        return rolePermissionRepository.exists(new RolePermissionPK(rid, pid));
    }
}
