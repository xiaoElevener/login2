package com.xiao.login.controller;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
     * @return
     */
    @RequestMapping("/loginForm")
    public String getLoginForm() {
        log.info("【获取登录表单】");
        return "user/login";
    }


    @RequestMapping("/login")
    public String login(@RequestParam("nickname") String nickname, @RequestParam("password") String password, Map<String, Object> map) {
        AuthenticationToken authenticationToken = new UsernamePasswordToken(nickname, password);
        try {
            SecurityUtils.getSubject().login(authenticationToken);
        }catch (UnknownAccountException exception) {
            map.put("message", ResultEnum.UNKNOWN_ACCOUNT.message);
            return "common/error";
        } catch (IncorrectCredentialsException exception) {
            map.put("message", ResultEnum.INCORRECT_CREDENTIALS.message);
            return "common/error";
          }  catch (LockedAccountException exception) {
            map.put("message", ResultEnum.LOCKED_ACCOUNT.message);
            return "common/error";
        } catch (AuthenticationException exception) {
            log.info("【异常类】exception={}", exception.getClass().getName());
            map.put("message", exception.getMessage());
            return "common/error";
        }
        log.info("【登陆成功】");
        map.put("roles", JsonUtil.toJson(userService.findRoles(nickname)));
        return "user/info";
    }


    /**
     * 登陆成功页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "user/info";
    }

//    shiro默认实现 /logout 有点问题
    @RequestMapping(value = "/logout")
    public String logout() {
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception exception) {
            log.error("【登出报错】exceotion={}",exception.getClass().getName());
        }
        log.info("【用户登出】");
        return "user/login";
    }


    @RequestMapping(value = "/error")
    public String error(Map<String, Object> map) {
        map.put("message", "用户名/密码错误");
        return "common/error";
    }

    @RequestMapping(value = "/403")
    public String Unauthorized(Map<String, Object> map) {
        map.put("message", "未授权!");
        return "common/error";
    }

}
