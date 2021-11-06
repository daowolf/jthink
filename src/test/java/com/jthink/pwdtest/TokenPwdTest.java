package com.jthink.pwdtest;

import com.jthink.security.util.LoginUtil;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;

public class TokenPwdTest {

	public static void main22(String[] args) {
		String pwdtest = SaSecureUtil.md5BySalt("123456", "salt");
		String sha256 = SaSecureUtil.sha256("wangtao" + "salt");
		String sha2561 = SaSecureUtil.sha256("wangtao" + "salt");
		System.out.println("密码:");
		System.out.println(pwdtest);
		System.out.println("sha256");
		System.out.println(sha256);
		System.out.println(sha2561);
		String salt = RandomUtil.randomString(5);
		System.out.println("salt:" + salt);
	}

	public static void main(String[] args) {
		String encodePass = LoginUtil.encodePwd("123456");
		System.out.println("encodepass=" + encodePass);
		boolean isMatch = LoginUtil.matchPass("123456", encodePass);
		System.out.println("isMatch=" + isMatch);
	}

}
