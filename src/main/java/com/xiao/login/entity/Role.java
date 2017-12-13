package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Administrator
 * @create 2017-12-13 11:07
 */
@Entity
@Table(name="u_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型
     */
    private String type;
}
