package com.xiao.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
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

    /**
     * 获取登录页面
     * @return
     */
    @RequestMapping("/loginForm")
    public String getLoginForm() {
        return "user/login";
    }


    @RequestMapping("/login")
    public void login(@RequestParam("nickname") String nickname,@RequestParam("password") String password) {
        AuthenticationToken authenticationToken = new UsernamePasswordToken(nickname, password);
    }


    /**
     * 登陆成功页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return  "user/info";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        //TODO
        return "user/login";
    }


    @RequestMapping(value = "/error")
    public String error(Map<String,Object> map){
        map.put("message", "用户名/密码错误");
        return "common/error";
    }

    @RequestMapping(value = "/403")
    public String Unauthorized(Map<String,Object> map){
        map.put("message", "未授权!");
        return "common/error";
    }

}
