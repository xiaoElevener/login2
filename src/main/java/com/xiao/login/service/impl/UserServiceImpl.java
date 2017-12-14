package com.xiao.login.service.impl;

import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.entity.UserRole;
import com.xiao.login.entity.cons.UserRolePK;
import com.xiao.login.repository.RoleRepository;
import com.xiao.login.repository.UserRepository;
import com.xiao.login.repository.UserRoleRepository;
import com.xiao.login.service.RoleService;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @create 2017-12-13 15:19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User createUser(User user) {
        //创建角色的时候对帐号加密
        user.setPswd(PasswordUtil.encryptBasedDes(user.getPswd()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public void changePassword(Integer uid, String newPassword) {
        User user = userRepository.findOne(uid);
        user.setPswd(PasswordUtil.encryptBasedDes(newPassword));
        userRepository.save(user);
    }


    //TODO 优化
    @Override
    public void correlationRoles(Integer userId, Integer... roleIds) {
        if (userId == null || roleIds.length == 0) {
            return;
        }
        for (Integer roleId : roleIds) {
            if (!exists(userId, roleId)) {
                userRoleRepository.save(new UserRole(userId, roleId));
            }
        }
    }

    @Override
    public void uncorrelationRoles(Integer userId, Integer... roleIds) {
        if (userId == null || roleIds.length == 0) {
            return;
        }
        for (Integer roleId : roleIds) {
            if (exists(userId, roleId)) {
                userRoleRepository.delete(userRoleRepository.findOne(new UserRolePK(userId, roleId)));
            }
        }
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public Map<String,Set<String>> findRolesAndPermission(String nickname){
        //通过用户名查找用户
        User user = userRepository.findByNickname(nickname);

        //通过uid查询 所有UserRole
        Set<UserRole> set = userRoleRepository.findByUid(user.getId());

        //如果该用户还未分配任何权限
        if (set == null)
            return null;

        HashSet<String> roles = new HashSet<>();
        HashSet<Integer> roleIdSet = new HashSet<Integer>();
        for (UserRole userRole : set
                ) {
            Role role=roleRepository.findOne(userRole.getRid());
            roles.add(role.getName());
            roleIdSet.add(role.getId());
        }

        Map<String, Set<String>> map = new HashMap<>();
        map.put("roles", roles);

        //通过roleIdSet查找permissionNameSet
        Set<String> permissionSet = roleService.findPermissions(roleIdSet);
        map.put("permissions", permissionSet);

        return map;
    }


    @Override
    public Set<String> findPermissions(String nickname) {





        return null;
    }

    public boolean exists(Integer uid, Integer rid) {
        return userRoleRepository.exists(new UserRolePK(uid, rid));
    }
}
