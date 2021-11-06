package com.jthink.security.util;

import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;

public class LoginUtil {
	public static String encodePwd(String passwordStr) {
		String salt = RandomUtil.randomString(5);
		String base64Pass = encodePwd(passwordStr, salt);
		return base64Pass;
	}

	public static String encodePwd(String passwordStr, String salt) {
		String hasPass = SaSecureUtil.sha256(passwordStr + salt);
		String fullPass = salt + hasPass;
		String base64 = SaBase64Util.encode(fullPass);
		return base64;
	}

	public static boolean matchPass(String whitePass, String hasPass) {
		String realPass = SaBase64Util.decode(hasPass);
		String salt = realPass.substring(0, 5);
		String base64Pass = encodePwd(whitePass, salt);
		if (base64Pass.equals(hasPass)) {
			return true;
		}

		return false;
	}
}
