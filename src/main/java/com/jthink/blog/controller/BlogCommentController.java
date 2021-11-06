package com.jthink.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jthink.cms.constants.CommentState;
import com.jthink.cms.entity.PostComment;
import com.jthink.cms.service.PostCommentService;
import com.jthink.cms.service.PostService;
import com.jthink.common.config.cache.JthinkCache;
import com.jthink.member.entity.Member;
import com.jthink.permission.service.UserService;
import com.jthink.security.exception.ValidateCodeException;
import com.jthink.security.util.JthinkConstant;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class BlogCommentController {
	@Autowired
	private PostService postService;
	@Autowired
	private PostCommentService postCommentService;
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private JthinkCache jthinkCache;

	@SaCheckLogin(type = "member")
	@RequestMapping("/member/post/sendComment")
	@ResponseBody
	public ResponseBo sendComment(PostComment comment, HttpServletRequest request, HttpServletResponse response) {
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
			e.printStackTrace();
			return ResponseBo.error(e.getMessage());
		}

		// 校验一次，本次key的验证码自动清除失效(默认基于本地内存)
		jthinkCache.removeItem(codeKey);
		comment.setCreateTime(new Date());
		comment.setCommentApproved(CommentState.AUDIT);
		Integer memberId = StpMemberUtil.getLoginIdAsInt();
		Member member = memberService.selectByKey(memberId);
		comment.setCommentAuthor(member.getNickname());
		comment.setUserId(memberId);
		// 审核中
		comment.setCommentApproved(CommentState.AUDIT);
		postCommentService.save(comment);
		postService.updateAddCommentCount(1, comment.getCommentPostId());
		return ResponseBo.ok();
	}
}
