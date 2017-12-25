package com.xiao.login.service;


import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.http.HttpSession;


/**
 * @author xiao_elevener
 * @date 2017-12-22 23:27
 */

public interface PasswordService {

    AuthenticationToken generalToken(String username, String password, boolean rememberMe, HttpSession session);

}
