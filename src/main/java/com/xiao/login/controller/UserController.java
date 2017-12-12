package com.xiao.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


/**
 * @author Administrator
 * @create 2017-12-12 12:17
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/index")
    public ModelAndView index() {
        log.info("【index】");
        return new ModelAndView("user/login");
    }


    /**
     * 成功跳转info
     * 报错跳转error
     * @return
     */
    @GetMapping(value = "/session")
    public ModelAndView login(){
        log.info("【用户登录】");
        return new ModelAndView("/user/info");
    }

    @DeleteMapping(value = "/session")
    public ModelAndView logout(){
        return new ModelAndView("user/login.ftl");
    }

    @GetMapping(value = "/error")
    public ModelAndView error(Map<String,Object> map){
        map.put("message", "错误提示");
        return new ModelAndView("user/error",map);
    }

}
