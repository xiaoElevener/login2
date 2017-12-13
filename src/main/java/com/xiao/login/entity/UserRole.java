package com.xiao.login.entity;

import com.xiao.login.entity.cons.UserRolePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Administrator
 * @create 2017-12-13 12:38
 */
@Entity
@Table(name = "u_user_role")
@IdClass(UserRolePK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    /**
     * 用户id
     */
    @Id
    private Integer uid;

    /**
     * 角色id
     */
    @Id
    private Integer rid;
}
