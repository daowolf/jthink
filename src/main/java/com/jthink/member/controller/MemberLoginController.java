package com.jthink.member.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.jthink.common.config.cache.JthinkCache;
import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.member.dto.MemberDTO;
import com.jthink.member.entity.Member;
import com.jthink.security.exception.ValidateCodeException;
import com.jthink.security.util.JthinkConstant;
import com.jthink.security.util.LoginUtil;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.stp.SaTokenInfo;

/**
 * 会员登录控制器
 * 
 */
@Controller
@RequestMapping("/member")
public class MemberLoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
	private JthinkCache jthinkCache;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@GetMapping("/login")
	public String loginPage() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/login";
	}

	@GetMapping("/register")
	public String register() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/register";
	}

	@RequestMapping("/dologin")
	@ResponseBody
	public ResponseBo doLogin(MemberDTO memberDto, HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException {
		ServletContext context = request.getServletContext();

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		jthinkCache = ctx.getBean(JthinkCache.class);
		String codeKey = request.getHeader(JthinkConstant.KEY_IMAGE_CODE);
		String codeInCache = jthinkCache.getKey(codeKey);
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request, "imageCode");
			if (StringUtils.isBlank(codeInRequest)) {
				throw new ValidateCodeException("验证码不能为空！");
			}
			if (codeInCache == null) {
				throw new ValidateCodeException("验证码不存在，请重新发送！");
			}
			if (!StringUtils.equalsIgnoreCase(codeInCache, codeInRequest)) {
				throw new ValidateCodeException("验证码不正确！");
			}
		} catch (ServletRequestBindingException e) {
			// e.printStackTrace();
			return ResponseBo.error(e.getMessage());
		}
		// 校验一次，本次key的验证码自动清除失效(默认基于本地内存)
		jthinkCache.removeItem(codeKey);
		Member member = memberService.findByMemberBynameOrEmail(memberDto.getUsername(), memberDto.getUsername());
		if (null != member && LoginUtil.matchPass(memberDto.getPassword(), member.getPassword())) {
			log.info(memberDto.getUsername() + "登录成功");
			StpMemberUtil.setLoginId(member.getId());
			SaTokenInfo tokenInfo = StpMemberUtil.getTokenInfo();
			return ResponseBo.ok(tokenInfo);
		}
		return ResponseBo.error("登录失败");

	}

	@RequestMapping("/logout")
	public String logOut() {
		StpMemberUtil.logout();
		return "redirect:/";
	}
}
