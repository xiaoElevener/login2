package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Administrator
 * @create 2017-12-13 11:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     * 角色id
     */

    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

}
