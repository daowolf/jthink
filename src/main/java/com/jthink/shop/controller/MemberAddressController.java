package com.jthink.shop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.member.controller.MemberBaseController;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.entity.MemberAddress;
import com.jthink.shop.service.MemberAddressService;

@Controller
@RequestMapping("/member/shop/address")
public class MemberAddressController extends MemberBaseController {
	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@RequestMapping({ "", "/index" })
	public String index() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/address";
	}

	@GetMapping("/add")
	public String addAddress() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/address-add";
	}

	@RequestMapping("/save")
	public String saveAddress(MemberAddress address) {
		address.setCreateTime(new Date());
		address.setMemberId(StpMemberUtil.getLoginIdAsInt());
		memberAddressService.save(address);
		return "redirect:/member/shop/address/index";
	}
}
