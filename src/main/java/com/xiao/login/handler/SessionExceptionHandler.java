package com.xiao.login.handler;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xiao_elevener
 * @date 2017-12-23 22:34
 */
@ControllerAdvice
@Slf4j
public class SessionExceptionHandler {

    @ExceptionHandler(value= UnknownSessionException.class)
    public ModelAndView unknownSessionException(UnknownSessionException exception, Model model) {
        model.addAttribute("message", ResultEnum.UNKONWN_SESSION);
        log.error("【未知sesionId】");
        return new ModelAndView("/common/error");
    }

}
