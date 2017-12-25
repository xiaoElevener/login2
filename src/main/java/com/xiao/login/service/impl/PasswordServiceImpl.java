package com.xiao.login.service.impl;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.exception.LoginException;
import com.xiao.login.service.PasswordService;
import com.xiao.login.utils.RSACoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.Map;

/**
 * @author xiao_elevener
 * @date 2017-12-23 21:37
 */
@Slf4j
@Service
public class PasswordServiceImpl implements PasswordService{

    @Override
    public AuthenticationToken generalToken(String username, String password, boolean rememberMe, HttpSession session){
        AuthenticationToken authenticationToken;
        try {
            //前端URIencode,把+替换为空格,后端把空格替换为+
            password = password.replaceAll("%2B", "+");
            Map<String, Key> stringKeyMap = (Map<String, Key>) session.getAttribute("RSA_KEY");
            log.info("【前端传过来的加密后的密码  和服务端的私钥】password={},privateKey={}", password, RSACoder.getPrivateKey(stringKeyMap));
            String decoderPassword = new String(RSACoder.decryptByPrivateKey(password, RSACoder.getPrivateKey(stringKeyMap)));
            authenticationToken = new UsernamePasswordToken(username, decoderPassword, rememberMe);
        }catch (Exception exception){
            log.error("【RSA解密错误】");
            throw new LoginException(ResultEnum.SYSTEM_ERROR);
        }
        return authenticationToken;
    }
}
