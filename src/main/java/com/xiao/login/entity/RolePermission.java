package com.xiao.login.entity;

import com.xiao.login.entity.cons.RolePermissionPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Administrator
 * @create 2017-12-13 12:41
 */
@Entity
@IdClass(RolePermissionPK.class)
@Table(name = "u_role_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable{
    private static final long serialVersionUID = -5995650425524832929L;
    /**
     * 角色id
     */
    @Id
    private Integer rid;

    /**
     * 权限id
     */
    @Id
    private Integer pid;

}
