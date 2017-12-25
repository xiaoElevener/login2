package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Administrator
 * @create 2017-12-13 11:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 权限名
     */
    private String permissionName;
}
