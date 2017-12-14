package com.xiao.login.config;

import com.xiao.login.realm.MyShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

        //配置添加权限
        //filterChainDefinitionMap.put("/system/add", "perms[权限添加]");

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
        return securityManage;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
         MyShiroRealm myShiroRealm=new MyShiroRealm();
         return myShiroRealm;
    }


}
