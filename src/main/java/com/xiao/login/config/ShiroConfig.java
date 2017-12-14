package com.xiao.login.config;

import com.xiao.login.Enum.PermissionEnum;
import com.xiao.login.Enum.RoleEnum;
import com.xiao.login.realm.MyShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置文件
 *
 * @author Administrator
 * @create 2017-12-13 13:38
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;



    /**
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {


        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置登陆的url
        shiroFilterFactoryBean.setLoginUrl("/system/loginForm");

        //设置登陆成功要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/system/index");

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/403");

        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的链接  顺序判断
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/system/loginForm", "anon");
        filterChainDefinitionMap.put("/system/login", "anon");

        //配置推出过滤器，其中具体的退出代码shiro已经替我们实现了
        filterChainDefinitionMap.put("/system/logout", "logout");

        //配置添加权限/角色
        filterChainDefinitionMap.put("/system/PM", "roles["+ RoleEnum.PM.roleName +"]");

        filterChainDefinitionMap.put("/system/PG", "roles["+ RoleEnum.PG.roleName +"]");

        filterChainDefinitionMap.put("/system/STE", "roles["+ RoleEnum.STE.roleName +"]");

        //
        filterChainDefinitionMap.put("/system/view","perms["+ PermissionEnum.VIEW.permissionName+"]");

        filterChainDefinitionMap.put("/system/create","perms["+ PermissionEnum.CREATE.permissionName+"]");

        filterChainDefinitionMap.put("/system/delete","perms["+ PermissionEnum.DELETE.permissionName+"]");




        // /**一般放最后配置，chain匹配是从上到下逐个执行
        //authc:utl必须通过认证过后才能匹配
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("【shiro拦截器注入】");
        return shiroFilterFactoryBean;


    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManage = new DefaultWebSecurityManager();
        //设置realm
        securityManage.setRealm(myShiroRealm());

        //自定义缓存实现，使用redis
        securityManage.setCacheManager(cacheManager());

        //自定义session管理
        securityManage.setSessionManager(SessionManager());

        //注入 remembermeManager
        securityManage.setRememberMeManager(rememberMeManager());
        return securityManage;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
         MyShiroRealm myShiroRealm=new MyShiroRealm();
         return myShiroRealm;
    }

    /**
     * 配置redisManager
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);
        return redisManager;
    }

    /**
     * cacheManager  redis实现
     * @return
     */

    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     *  RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 设置rememberMe cookie
     */
    public SimpleCookie rememberMeCookie() {
        //参数为前端checkbox 的name

        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");

        //生效时间  30天
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;

    }


    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

        cookieRememberMeManager.setCipherKey(Base64.decode("d2hvIGkgYW0="));
        return cookieRememberMeManager;
    }

}
