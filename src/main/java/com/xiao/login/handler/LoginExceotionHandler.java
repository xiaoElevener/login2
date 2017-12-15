package com.xiao.login.handler;

import com.xiao.login.Exception.LoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 配置全局异常捕获
 * @author Administrator
 */
@ControllerAdvice
public class LoginExceotionHandler {

    @ExceptionHandler(value= LoginException.class)
    public ModelAndView handleAuthorizeException(LoginException exception,Model model) {
        model.addAttribute("message", exception.getMessage());
        return new ModelAndView("/common/error");
    }



}
