package com.xiao.login.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author Administrator
 * @create 2017-12-13 12:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    /**
     * 用户id
     */
    @Id
    private Integer userId;

    /**
     * 角色id
     */
    @Id
    private Integer roleId;
}
