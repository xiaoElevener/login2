package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Administrator
 * @create 2017-12-13 11:30
 */
@Entity
@Table(name = "u_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    /**
     * 权限id
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    /**
     * 权限url
     */
    private String url;

    /**
     * 权限名
     */
    private String name;
}
