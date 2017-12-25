package com.xiao.login.realm;

import com.xiao.login.entity.User;
import com.xiao.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的realm
 *
 * @author Administrator
 * @create 2017-12-13 14:56
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {


    /**
     * 用户登录次数计数 redisKey 前缀
     */
    public static final String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    /**
     * 用户登录是否被锁定  一小时  redisKey前缀
     */
    public static final String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 锁定字符串
     */
    public static final String LOCK = "LOCK";

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public MyShiroRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    /**
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = (String) SecurityUtils.getSubject().getPrincipal();

        //查询拥有角色及权限

        List<String> roles = userService.findRoles(userName);
        info.setRoles(new HashSet<>(roles));


        List<String> permissions = userService.findPermissions(userName);
        info.setStringPermissions(new HashSet<>(permissions));

        return info;
    }


    /**
     * 身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //在登陆之前对线程中已经登陆的用户进行下线
        if (SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().logout();
        }
        String userName = (String) authenticationToken.getPrincipal();
        //先查询缓存中的数据
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        //计数大于5时，设置用户被锁定一小时
        String shiroLoginCountUserName = SHIRO_LOGIN_COUNT + userName;
        if (hasKey(shiroLoginCountUserName) &&isUserLocked(shiroLoginCountUserName)) {
            opsForValue.set(shiroLoginCountUserName, LOCK, 1, TimeUnit.HOURS);
        }
        //缓存添加登陆次数
        opsForValue.increment(shiroLoginCountUserName, 1);
        if (LOCK.equals(opsForValue.get(shiroLoginCountUserName))) {
            throw new LockedAccountException();
        }
        //通过用户名去查询User
        User user = userService.getUser(userName);

        if (user == null) {
            log.error("【登录失败】无此帐号");
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
    }


    private boolean hasKey(String keyName) {
        return stringRedisTemplate.hasKey(keyName);
    }

    public boolean isUserLocked(String shiroLoginCountUserName) {
        return Integer.parseInt(stringRedisTemplate.opsForValue().get(shiroLoginCountUserName)) >= 5;
    }

}
