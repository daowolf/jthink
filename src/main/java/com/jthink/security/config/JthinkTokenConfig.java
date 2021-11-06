package com.jthink.security.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jthink.security.code.ValidateCodeGenerator;
import com.jthink.security.code.img.ImageCodeGenerator;
import com.jthink.security.util.StpMemberUtil;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;

@Configuration
public class JthinkTokenConfig implements WebMvcConfigurer {
	// 注册sa-token的拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 启用注解可以和下面路由拦截器(SaRouteInterceptor)同时使用
		registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");

		// 注册路由拦截器，自定义验证规则
		registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
			// 登录验证 -- 排除多个路径
			SaRouter.match(Arrays.asList("/system/**"),
					Arrays.asList("/static/**", "/system/adminlogin", "/system/dologin", "/system/adminlogout"),
					() -> StpUtil.checkLogin());

			// 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
			SaRouter.match("/admin/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));

			// 权限认证 -- 不同模块, 校验不同权限
			SaRouter.match("/user/**", () -> StpUtil.checkPermission("user"));
			SaRouter.match("/admin/**", () -> StpUtil.checkPermission("admin"));
			SaRouter.match("/goods/**", () -> StpUtil.checkPermission("goods"));
			SaRouter.match("/orders/**", () -> StpUtil.checkPermission("orders"));
			SaRouter.match("/notice/**", () -> StpUtil.checkPermission("notice"));
			SaRouter.match("/comment/**", () -> StpUtil.checkPermission("comment"));

			// 匹配 restful 风格路由
			SaRouter.match("/article/get/{id}", () -> StpUtil.checkPermission("article"));

			// 在多账号模式下，可以使用任意StpUtil进行校验
			SaRouter.match(Arrays.asList("/member/**"),
					Arrays.asList("/member/login", "/member/dologin", "/member/register", "/member/logout"),
					() -> StpMemberUtil.checkLogin());

		})).addPathPatterns("/**");
	}

	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		return imageCodeGenerator;
	}
}
