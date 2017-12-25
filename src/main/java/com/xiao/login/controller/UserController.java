package com.xiao.login.controller;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.exception.LoginException;
import com.xiao.login.service.PasswordService;
import com.xiao.login.service.UserService;
import com.xiao.login.utils.JsonUtil;
import com.xiao.login.utils.RSACoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.Map;


@Controller
@RequestMapping("/system")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    /**
     * 获取登录页面
     *
     * @return 登陆页面
     */
    @RequestMapping("/loginForm")
    public String getLoginForm(HttpSession session, Map<String, String> map) {
        //TODO 两次请求
        //临时解决
        if (session.getAttribute("RSA_KEY") == null) {
            try {
                Map<String, Key> stringKeyMap = RSACoder.initKey();
                session.setAttribute("RSA_KEY", stringKeyMap);
                map.put("publicKey", RSACoder.getPublicKey(stringKeyMap));
                log.info("【生成的公钥】publicKey={}", RSACoder.getPublicKey(stringKeyMap));
            } catch (Exception exception) {
                log.info("生成公私钥报错!\n");
                exception.printStackTrace();
                throw new LoginException(ResultEnum.SYSTEM_ERROR);
            }
        }else {
            try {
                map.put("publicKey", RSACoder.getPublicKey((Map<String, Key>)session.getAttribute("RSA_KEY")));
            }catch (Exception e){
                log.error("【提取不到key】");
                throw new LoginException(ResultEnum.SYSTEM_ERROR);
            }
        }
        return "user/loginForm";
    }


    @RequestMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "rememberMe",
            required = false, defaultValue = "false") boolean rememberMe, Map<String, Object> map,
                      HttpSession session, ServletRequest request, ServletResponse response) {
        try {
            AuthenticationToken token=passwordService.generalToken(username,password,rememberMe,session);
            SecurityUtils.getSubject().login(token);
            //登陆成功
            session.removeAttribute("RSA_KEY");
            WebUtils.redirectToSavedRequest(request, response, "/system/index");
        } catch (UnknownAccountException exception) {
            throw new LoginException(ResultEnum.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException exception) {
            throw new LoginException(ResultEnum.INCORRECT_CREDENTIALS);
        } catch (LockedAccountException exception) {
            throw new LoginException(ResultEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException exception) {
            throw new LoginException(ResultEnum.SYSTEM_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(ResultEnum.SYSTEM_ERROR);
        }

    }


    /**
     * 登陆成功页面
     *
     * @return 登陆成功页面
     */
    @RequestMapping(value = "/index")
    public String index(Map<String, Object> map) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        map.put("info", JsonUtil.toJson(userService.findRoles(userName)) + JsonUtil.toJson(userService.findPermissions(userName)));
        return "user/info";
    }


    @RequestMapping(value = "/authc")
    public String authc(Map<String, Object> map) {
        map.put("message", ResultEnum.AUTHC_SUCCESS.message);
        return "system/success";
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

    @RequestMapping("/user")
    public String user(Map<String, Object> map) {
        map.put("message", ResultEnum.TEST_USER.message);
        return "user/success";
    }




}
