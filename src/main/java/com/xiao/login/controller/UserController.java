package com.xiao.login.controller;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.Exception.LoginException;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取登录页面
     *
     * @return 登陆页面
     */
    @RequestMapping("/loginForm")
    public String getLoginForm() {
        log.info("【获取登录表单】");
        return "user/login";
    }


    @RequestMapping("/login")
    public String login(@RequestParam("nickname") String nickname, @RequestParam("password") String password,@RequestParam(value="rememberMe",
            required = false,defaultValue = "false") boolean rememberMe, Map<String, Object> map,
            HttpServletResponse response) {
        AuthenticationToken authenticationToken = new UsernamePasswordToken(nickname, password,rememberMe);
        try {
            SecurityUtils.getSubject().login(authenticationToken);
        } catch (UnknownAccountException exception) {
            throw new LoginException(ResultEnum.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException exception) {
            throw new LoginException(ResultEnum.INCORRECT_CREDENTIALS);
        } catch (LockedAccountException exception) {
            throw new LoginException(ResultEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException exception) {
            throw new LoginException(ResultEnum.SYSTEM_ERROR);
        }
        log.info("【登陆成功】");
        return "forward:/system/index";
    }


    /**
     * 登陆成功页面
     *
     * @return 登陆成功页面
     */
    @RequestMapping(value = "/index")
    public String index( Map<String, Object> map) {
        String nickname= (String) SecurityUtils.getSubject().getPrincipal();
        log.info("【index】Principal={}",nickname);
        map.put("info", JsonUtil.toJson(userService.findRolesAndPermission(nickname)));
        return "user/info";
    }

    //    shiro默认实现 /logout 有点问题
    @RequestMapping(value = "/logout")
    public String logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception exception) {
            throw new LoginException(ResultEnum.SYSTEM_ERROR);
        }
        log.info("【用户登出】");
        return "user/login";
    }


    /**
     * 验证产品经理的权限
     *
     * @return 验证成功页面
     */
    @RequestMapping(value = "/PM")
    public String verifyPM(Map<String, Object> map) {
        map.put("message", ResultEnum.PM_SUCCESS.message);
        return "user/success";
    }

    /**
     * 验证测试人员角色
     *
     * @return 验证成功页面
     */
    @RequestMapping(value = "/STE")
    public String verifySTE(Map<String, Object> map) {
        map.put("message", ResultEnum.STE_SUCCESS.message);
        return "user/success";
    }

    /**
     * 验证开发人员角色
     *
     * @return 验证成功页面
     */
    @RequestMapping(value = "/PG")
    public String verifyPG(Map<String, Object> map) {
        map.put("message", ResultEnum.PG_SUCCESS.message);
        return "user/success";
    }

    /**
     * 无权访问跳转
     *
     * @return 无权页面
     */
    @RequestMapping("/403")
    public String permissionFailed(Map<String, Object> map) {
        map.put("error", ResultEnum.PERMISSION_FAILED.message);
        return "common/403";
    }

    /**
     * 测试查看权限
     *
     * @return 成功页面
     */
    @RequestMapping("/view")
    public String view(Map<String, Object> map) {
        map.put("message", ResultEnum.PERMISSION_SUCCESS.message);
        return "user/success";
    }

    /**
     * 测试创建权限
     *
     * @return 成功页面
     */
    @RequestMapping("/create")
    public String create(Map<String, Object> map) {
        map.put("message", ResultEnum.PERMISSION_SUCCESS.message);
        return "user/success";
    }

    /**
     * 测试删除权限
     *
     * @return 成功页面
     */
    @RequestMapping("/delete")
    public String delete(Map<String, Object> map) {
        map.put("message", ResultEnum.PERMISSION_SUCCESS.message);
        return "user/success";
    }

}
