package com.xiao.login.Enum;

import lombok.Getter;

@Getter
public enum UserStatusEnum implements CodeEnum {

	UP(0, "激活"), DOWN(1, "禁止");

	final public Integer code;

	final public String message;

	private UserStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

}
