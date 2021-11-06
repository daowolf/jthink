package com.jthink.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jthink.directive.ThymeleafVariUtil;

@Controller
@RequestMapping("/member/comment")
public class MemberCommentsController extends MemberBaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@RequestMapping({ "", "/index" })
	public String index() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/member-comment";
	}
}
