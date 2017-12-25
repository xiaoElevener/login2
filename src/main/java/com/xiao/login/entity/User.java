package com.xiao.login.entity;

import com.xiao.login.Enum.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Administrator
 * @create 2017-12-13 11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date userLastLoginTime;

    /**
     * 状态码 1:有效，0:禁止登录 默认为有效
     */
    private Integer userStatus= UserStatusEnum.UP.getCode();
}
