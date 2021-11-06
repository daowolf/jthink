package com.jthink.blog.controller;

import com.jthink.common.service.JoptionService;
import com.jthink.directive.ThymeleafVariUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogIndex {
	@Autowired
	private JoptionService joptionService;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@RequestMapping({ "", "/index" })
	public String index() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/index";
	}
}
