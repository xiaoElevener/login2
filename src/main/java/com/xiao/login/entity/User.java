package com.xiao.login.entity;

import com.xiao.login.Enum.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 * @create 2017-12-13 11:30
 */
@Entity
@Table(name="u_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String pswd;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 状态码 1:有效，0:禁止登录 默认为有效
     */
    private Integer status= UserStatusEnum.UP.getCode();
}
