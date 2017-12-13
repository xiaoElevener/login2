package com.xiao.login.entity.cons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Role和Perimission的复合主键类
 * @author Administrator
 * @create 2017-12-13 13:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionPK implements Serializable{
    private static final long serialVersionUID = 5939779787861039452L;

    private Integer rid;

    private Integer pid;



}
