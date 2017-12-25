package com.xiao.login.exception;

import com.xiao.login.Enum.ResultEnum;
import lombok.Getter;

/**
 * 统一异常处理
 * @author xiao_elevener
 * @create 2017-12-13 21:05
 */
@Getter
public class LoginException extends RuntimeException{

    private Integer code;

    public LoginException(ResultEnum resultEnum) {
        super(resultEnum.message);
        this.code = resultEnum.code;
    }

    public LoginException(Integer code, String message) {
        super(message);
        this.code = code;
    }


}
