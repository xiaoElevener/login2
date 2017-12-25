package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author Administrator
 * @create 2017-12-13 12:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission{
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

}
