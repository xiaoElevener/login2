package com.xiao.login.entity.cons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和角色复合主键
 *
 * @author Administrator
 * @create 2017-12-13 13:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolePK implements Serializable {

    private static final long serialVersionUID = -6234387842629758293L;

    private Integer uid;

    private Integer rid;

}
