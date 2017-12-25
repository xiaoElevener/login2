package com.xiao.login.matcher;

import com.xiao.login.realm.MyShiroRealm;
import com.xiao.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao_elevener
 * @date 2017-12-21 10:17
 */
@Slf4j
public class MyCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        //token.getCredentials返回类型是char[]
        char[] tokenPassword=(char[])token.getCredentials();
        String stringTokenPassword=new String(tokenPassword);
        //info.getCredentials返回的是 在realm查询时候放进去的类型
        String infoPassword= (String)info.getCredentials();

        log.info("【matcher】通过自定义matcher匹配 tokenPassword={},infoPassword={}",stringTokenPassword,infoPassword);
        boolean result=stringTokenPassword.equals(infoPassword);
        if(result){
           stringRedisTemplate.opsForValue().getOperations().expire(MyShiroRealm.SHIRO_LOGIN_COUNT + token.getPrincipal(), 0, TimeUnit.SECONDS);
        }

        return result;
    }
}
