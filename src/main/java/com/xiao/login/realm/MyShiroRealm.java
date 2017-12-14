package com.xiao.login.realm;

import com.xiao.login.Enum.UserStatusEnum;
import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.service.UserService;
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

import java.util.Map;
import java.util.Set;

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

        if(SecurityUtils.getSubject().isAuthenticated()){
            SecurityUtils.getSubject().logout();
        }
        String nickname= (String) authenticationToken.getPrincipal();
        String password=new String((char[]) authenticationToken.getCredentials());
        User user = userService.findByNickname(nickname);

        if (user == null) {
            log.error("【登录失败】无此帐号");
            throw new UnknownAccountException();//没找到账号
        }

        if(!user.getPswd().equals(password)){
            log.error("【登录失败】密码错误 psd={},psd2={}",user.getPswd(),password);
            throw new IncorrectCredentialsException();//密码错误
        }

        if (user.getStatus().equals(UserStatusEnum.DOWN.code)) {
            log.error("【登录失败】帐号未激活");
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredetiasMatcher进行密码匹配
        //TODO 这里可以添加salt进行加密
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getNickname(), user.getPswd(), getName());

        log.info("【用户登录】={}",user);
        return authenticationInfo;
    }


}
