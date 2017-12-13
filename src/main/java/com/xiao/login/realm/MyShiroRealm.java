package com.xiao.login.realm;

import com.xiao.login.Enum.UserStatusEnum;
import com.xiao.login.entity.User;
import com.xiao.login.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的realm
 *
 * @author Administrator
 * @create 2017-12-13 14:56
 */
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
        return null;
    }


    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String nickname= (String) authenticationToken.getPrincipal();

        User user = userService.findByNickname(nickname);

        if (user == null) {
            throw new UnknownAccountException();//没找到账号
        }

        if (user.getStatus().equals(UserStatusEnum.DOWN.code)) {
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredetiasMatcher进行密码匹配
        //TODO 这里可以添加salt进行加密
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getNickname(), user.getPswd(), getName());
        return authenticationInfo;
    }
}
