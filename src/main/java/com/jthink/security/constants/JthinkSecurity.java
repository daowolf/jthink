package com.jthink.security.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.jthink.security.properties.ValidateCodeProperties;

@ConfigurationProperties(prefix = "jthink.security")
public class JthinkSecurity {
	// 菜单
	public static String MenuType = "0";
	// 按钮
	public static String ButtonType = "1";
	private ValidateCodeProperties code = new ValidateCodeProperties();

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

}
