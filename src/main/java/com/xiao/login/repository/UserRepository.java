package com.xiao.login.repository;

import com.xiao.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @create 2017-12-13 13:35
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByNickname(String nickname);
}
