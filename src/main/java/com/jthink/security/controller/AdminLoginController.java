package com.jthink.security.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.jthink.common.config.cache.JthinkCache;
import com.jthink.permission.dto.UserDTO;
import com.jthink.permission.entity.JthinkUser;
import com.jthink.permission.service.UserService;
import com.jthink.security.exception.ValidateCodeException;
import com.jthink.security.util.JthinkConstant;
import com.jthink.security.util.LoginUtil;
import com.jthink.utils.ResponseBo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录控制器
 * 
 */
@Controller
@RequestMapping("/system")
public class AdminLoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	private JthinkCache jthinkCache;
	@GetMapping("/adminlogin")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/dologin")
	@ResponseBody
	public ResponseBo doLogin(UserDTO user, HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
		ServletContext context = request.getServletContext();

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		jthinkCache = ctx.getBean(JthinkCache.class);
		String codeKey = request.getHeader(JthinkConstant.KEY_IMAGE_CODE);
		String codeInCache = jthinkCache.getKey(codeKey);
		String codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空！");
		}
		if (codeInCache == null) {
			throw new ValidateCodeException("验证码不存在，请重新发送！");
		}
		if (!StringUtils.equalsIgnoreCase(codeInCache, codeInRequest)) {
			throw new ValidateCodeException("验证码不正确！");
		}
		// 校验一次，本次key的验证码自动清除失效(默认基于本地内存)
		jthinkCache.removeItem(codeKey);
		JthinkUser jthinkUser = userService.findByUsernameOrEmail(user.getUsername(), user.getUsername());
		if (null!=jthinkUser && LoginUtil.matchPass(user.getPassword(), jthinkUser.getPassword())) {
			log.info(user.getUsername() + "登录成功");
			StpUtil.setLoginId(jthinkUser.getId());
			SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
			return ResponseBo.ok(tokenInfo);
		}
		return ResponseBo.error("登录失败");

	}

	@RequestMapping("/adminlogout")
	public String logOut() {
		StpUtil.logout();
		return "redirect:/system/adminlogin";
	}
}
