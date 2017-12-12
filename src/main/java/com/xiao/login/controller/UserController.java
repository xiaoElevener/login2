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
 */
@Controller
@RequestMapping("/system")
@Slf4j
public class UserController {

    @GetMapping("/index")
    public String index() {
        log.info("【index】");
        return "user/login";
    }


    /**
     * 成功跳转info
     * 报错跳转error
     */
    @GetMapping(value = "/session")
    public String login(){
        log.info("【用户登录】");
        return  "user/info";
    }

    @DeleteMapping(value = "/session")
    public String logout(){
        return "user/login";
    }

    @GetMapping(value = "/error")
    public String error(Map<String,Object> map){
        map.put("message", "【这是个友好的提示】");
        return "common/error";
    }

}
