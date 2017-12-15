package com.xiao.login.realm;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.Enum.UserStatusEnum;
import com.xiao.login.Exception.LoginException;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的realm
 *
 * @author Administrator
 * @create 2017-12-13 14:56
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数 redisKey 前缀
    private final String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定  一小时  redisKey前缀
    private final String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【权限验证】 principaCollection={}",principalCollection);
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        String nickname = (String) SecurityUtils.getSubject().getPrincipal();



        //查询拥有角色及权限

        Map<String, Set<String>> map = userService.findRolesAndPermission(nickname);
        if(map.get("roles") != null && (map.get("roles").size()!=0)){
            info.setRoles(map.get("roles"));
        }

        if(map.get("permissions") != null && (map.get("permissions").size()!=0)){
            info.setStringPermissions(map.get("permissions"));
        }

        log.info("【授权】={}",map);

        return info;
    }


    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("【用户进行登陆验证】");
        if(SecurityUtils.getSubject().isAuthenticated()){
            SecurityUtils.getSubject().logout();
        }
        log.info("【1111】");
        //用户输入的昵称和密码
        String nickname= (String) authenticationToken.getPrincipal();
        String password=new String((char[]) authenticationToken.getCredentials());


        log.info("【2222】");
        //先查询缓存中的数据
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        log.info("【3333】");
        //计数大于5时，设置用户被锁定一小时
        if(stringRedisTemplate.hasKey(SHIRO_LOGIN_COUNT+nickname) && Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+nickname))>=5){
            opsForValue.set(SHIRO_IS_LOCK + nickname, "LOCK", 1, TimeUnit.HOURS);
        }

        log.info("【用户登录】用户没被锁定");
        opsForValue.increment(SHIRO_LOGIN_COUNT+nickname,1);
        log.info("【用户尝试】");


        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+nickname))) {
            throw new LockedAccountException();
        }



        //通过昵称去查询User
        User user = userService.findByNickname(nickname);

        if (user == null) {
            log.error("【登录失败】无此帐号");
            throw new UnknownAccountException();//没找到账号
        }

        //从数据库读取需要解密再比较
        if(!PasswordUtil.decryptBasedDes(user.getPswd()).equals(password)){
            log.error("【登录失败】密码错误 psd={},psd2={}",user.getPswd(),password);
            throw new IncorrectCredentialsException();//密码错误
        }

        if (user.getStatus().equals(UserStatusEnum.DOWN.code)) {
            log.error("【登录失败】帐号未激活");
            throw new LockedAccountException();
        }

        //返回身份凭证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(nickname, password, getName());

        //更新user登录时间
        user.setLastLoginTime(new Date());
        userService.updateUser(user);

        //清空登陆次数(设置过期时间为0)
        opsForValue.getOperations().expire(SHIRO_LOGIN_COUNT + nickname, 0, TimeUnit.SECONDS);

        log.info("【用户登录】={}",user);

        return authenticationInfo;
    }


}
