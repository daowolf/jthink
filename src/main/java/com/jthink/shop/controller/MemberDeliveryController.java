package com.jthink.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jthink.member.controller.MemberBaseController;
import com.jthink.shop.service.DeliveryCompanyService;

@Controller
@RequestMapping("/system/shop/deliveryCompany")
public class MemberDeliveryController extends MemberBaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeliveryCompanyService deliveryCompanyService;
	
}
