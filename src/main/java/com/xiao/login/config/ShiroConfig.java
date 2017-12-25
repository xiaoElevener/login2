package com.xiao.login.config;

import com.xiao.login.filter.KickoutSessionControlFilter;
import com.xiao.login.listener.MySessionListener;
import com.xiao.login.matcher.MyCredentialsMatcher;
import com.xiao.login.realm.MyShiroRealm;
import com.xiao.login.utils.FilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.*;


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

    @Value("${spring.redis.timeout}")
    private int expire;

    @Value("${shiro.loginUrl}")
    private String loginUrl;

    @Value("${shiro.successUrl}")
    private String successUrl;

    @Value("${shiro.unauthorizedUrl}")
    private String unauthorizedUrl;


    @Value("${shiro.filterUrl}")
    private String filterUrlPath;


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
        shiroFilterFactoryBean.setLoginUrl(loginUrl);

        //设置登陆成功要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl(successUrl);

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        //设置拦截器集合
        shiroFilterFactoryBean.setFilters(filters());

        try {
            shiroFilterFactoryBean.setFilterChainDefinitionMap(FilterUtil.loadFromProperties(filterUrlPath));
        } catch (IOException e) {
            log.error("【shiro初始化】文件读取错误");
        }
        log.info("【shiro拦截器注入】");
        return shiroFilterFactoryBean;


    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());

        //自定义缓存实现，使用redis
        securityManager.setCacheManager(cacheManager());

        //自定义session管理
        securityManager.setSessionManager(sessionManager());

        //注入 remembermeManager
        securityManager.setRememberMeManager(rememberMeManager());



        return securityManager;
    }


    @Bean
    public MyShiroRealm myShiroRealm(){
         //set注入Matcher
         MyShiroRealm myShiroRealm=new MyShiroRealm(myCredentialsMatcher());
         return myShiroRealm;
    }


    /**
     * 配置自己的matcher
     */
    @Bean
    public MyCredentialsMatcher myCredentialsMatcher(){
        MyCredentialsMatcher myCredentialsMatcher=new MyCredentialsMatcher();;
        return myCredentialsMatcher;
    }

    /**
     * 配置redisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(expire);
        return redisManager;
    }

    /**
     * cacheManager  redis实现
     * @return
     */

    private RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * 使用基于内存的sessionDao
     */
    @Bean
    public SessionDAO sessionDAO() {
        SessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }

    /**
     * shiro session的管理
     */
    private DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        //默认使用JSESSIONID Cookie维护会话
        sessionManager.setSessionDAO(sessionDAO());

        //不配置监听器默认创建个空个监听器集合
        sessionManager.setSessionListeners(sessionListeners());

        //用简单cookie保存会话id
        sessionManager.setSessionIdCookie(new SimpleCookie("shiro_cookie"));

        //全局session的默认过期时间，默认是30分钟,由AbstractSessionManager配置

        //AbstractValidatingSessionManager 会创建默认的SessionValidationScheduler，用于定期的检测会话是否过期

        return sessionManager;
    }

    /**
     * 配置sessionManager监听器集合
     */

    private Collection<SessionListener> sessionListeners(){
        List<SessionListener> list=new ArrayList<>();
        list.add(new MySessionListener());
        return list;
    }

    private SimpleCookie rememberMeCookie() {
        //参数为前端checkbox 的name
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //生效时间  30天
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }


    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //AbstractRememberMeManager 构造函数会默认生成cipherKey
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    //踢出拦截器
    private KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter filter = new KickoutSessionControlFilter();
        filter.setCacheManager(cacheManager());
        //默认踢出前者
        filter.setKickoutAfter(false);
        filter.setMaxSession(1);
        filter.setKickoutUrl(loginUrl);
        filter.setSessionManager(sessionManager());
        return filter;
    }


    /**
     * 拦截器集合
     * @return
     */
    private Map<String, Filter> filters(){
        Map<String, Filter> filterMap =new HashMap<>();
        filterMap.put("kickout",kickoutSessionControlFilter());
        return  filterMap;
    }

}
