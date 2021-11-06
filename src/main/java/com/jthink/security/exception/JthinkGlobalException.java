package com.jthink.security.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;

/**
 * 全局异常处理
 */
@RestControllerAdvice // 可指定包前缀，比如：(basePackages = "com.pj.admin")
public class JthinkGlobalException {
	/**
	 * SpringBoot获取当前环境代码,Spring获取当前环境代码
	 */
	@Value("${spring.profiles.active}")
	private String profiles;

	/**
	 * 判断是否是Ajax请求
	 *
	 * @param request
	 * @return
	 */
	public boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	// 在当前类每个方法进入之前触发的操作
	@ModelAttribute
	public void get(HttpServletRequest request) throws IOException {

	}

	// 全局异常拦截（拦截项目中的所有异常）
	@ExceptionHandler
	public ResponseBo handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if ("dev".equals(profiles)) {
			// 打印堆栈，以供调试
			e.printStackTrace();
		}

		// 不同异常返回不同状态码
		ResponseBo aj = null;
		if (e instanceof NotLoginException) { // 如果是未登录异常
			NotLoginException ee = (NotLoginException) e;
			aj = ResponseBo.error(ee.getMessage());
			if (ee.getLoginType().equals("member") && !isAjax(request)) {
				response.sendRedirect("/member/login");
			} else if (!isAjax(request)) {
				response.sendRedirect("/system/adminlogin");
			} else {
				aj = ResponseBo.error("请登录");
			}
		} else if (e instanceof NotRoleException) { // 如果是角色异常
			NotRoleException ee = (NotRoleException) e;
			aj = ResponseBo.error("无此角色：" + ee.getRole());
		} else if (e instanceof NotPermissionException) { // 如果是权限异常
			NotPermissionException ee = (NotPermissionException) e;
			aj = ResponseBo.error("无此权限：" + ee.getCode());
		} else { // 普通异常, 输出：500 + 异常信息
			aj = ResponseBo.error(e.getMessage());
		}

		// 返回给前端
		return aj;

		// 输出到客户端
//		response.setContentType("application/json; charset=utf-8"); // http说明，我要返回JSON对象
//		response.getWriter().print(new ObjectMapper().writeValueAsString(aj));
	}

	// 全局异常拦截（拦截项目中的NotLoginException异常）
//	@ExceptionHandler(NotLoginException.class)
//	public ResponseBo handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 打印堆栈，以供调试
//		nle.printStackTrace(); 
//		
//		// 判断场景值，定制化异常信息 
//		String message = "";
//		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
//			message = "未提供token";
//		}
//		else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
//			message = "token无效";
//		}
//		else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
//			message = "token已过期";
//		}
//		else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
//			message = "token已被顶下线";
//		}
//		else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
//			message = "token已被踢下线";
//		}
//		else {
//			message = "当前会话未登录";
//		}
//		
//		// 返回给前端
//		return ResponseBo.error(message);
//	}

}
