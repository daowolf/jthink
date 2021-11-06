package com.jthink.member.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.member.entity.Member;
import com.jthink.member.enums.TradeStatus;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.entity.CartItem;
import com.jthink.shop.entity.Coupon;
import com.jthink.shop.entity.CouponMember;
import com.jthink.shop.entity.MemberAddress;
import com.jthink.shop.entity.MemberOrder;
import com.jthink.shop.entity.OrderDelivery;
import com.jthink.shop.entity.OrderInvoice;
import com.jthink.shop.entity.OrderItem;
import com.jthink.shop.entity.ShoppingCart;
import com.jthink.shop.facade.OrderFacade;
import com.jthink.shop.service.CouponMemberService;
import com.jthink.shop.service.CouponService;
import com.jthink.shop.service.MemberAddressService;
import com.jthink.shop.service.MemberOrderService;
import com.jthink.shop.service.MemberService;
import com.jthink.shop.service.OrderDeliveryService;
import com.jthink.shop.service.OrderInvoiceService;
import com.jthink.shop.service.OrderItemService;
import com.jthink.shop.service.ShoppingCartService;
import com.jthink.utils.ResponseBo;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/member/order")
public class MemberOrderController extends MemberBaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CouponMemberService couponMemberService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private MemberOrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderInvoiceService orderInvoiceService;
	@Autowired
	private OrderDeliveryService orderDeliveryService;
	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	private OrderFacade orderFacade;

	@RequestMapping({ "", "/index" })
	public String index() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/member-order";
	}

	@PostMapping("/generateOrder")
	@ResponseBody
	public ResponseBo saveOrder(HttpServletRequest request, HttpServletResponse response, MemberOrder order,
			Integer addrId) {
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer memberId = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(memberId);
		}
		String key = shoppingCartService.getKey(request, response, member);
		ShoppingCart cacheCart = shoppingCartService.mergeCart(key, member);
		List<CartItem> items = cacheCart.getCartItems();
		if (null == items || items.size() == 0) {
			return ResponseBo.error("购物车为空");
		}
		String couponUserId = request.getParameter("couponUserId");
		Coupon coupon = null;
		CouponMember couponUser = null;
		if (StringUtils.isNotBlank(couponUserId)) {
			// 判断优惠券的所有人
			Example couponUserExample = new Example(CouponMember.class);
			couponUserExample.createCriteria().andEqualTo("id", couponUserId).andEqualTo("couStatus", 1)
					.andEqualTo("userId", member.getId());
			List<CouponMember> couponUsers = couponMemberService.selectByExample(couponUserExample);
			if (null == couponUsers || couponUsers.size() == 0) {
				return ResponseBo.error("优惠券信息错误");
			}
			couponUser = couponUsers.get(0);
			if (couponUser.getCouStatus() > 1) {
				return ResponseBo.error("优惠券失效");
			}
			coupon = couponService.selectByKey(couponUser.getCouponId());
		}
		MemberAddress address = memberAddressService.selectByKey(addrId);
		if (null == address) {
			return ResponseBo.error("配送地址有误");
		}
		try {
			orderFacade.saveOrder(order, coupon, member, address, items, couponUser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error(e.getMessage());
		}
		// 操作成功清空购物车
		shoppingCartService.clearCart(request, response, member);
		return ResponseBo.ok(order);
	}

	@PostMapping("/generateSkuOrder")
	@ResponseBody
	public ResponseBo saveSkuOrder(HttpServletRequest request, HttpServletResponse response, MemberOrder order,
			Integer addrId) {
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer memberId = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(memberId);
		}
		String key = shoppingCartService.getKey(request, response, member);
		ShoppingCart cacheCart = shoppingCartService.mergeCart(key, member);
		List<CartItem> items = cacheCart.getCartItems();
		if (null == items || items.size() == 0) {
			return ResponseBo.error("购物车为空");
		}
		String couponUserId = request.getParameter("couponUserId");
		Coupon coupon = null;
		CouponMember couponUser = null;
		if (StringUtils.isNotBlank(couponUserId)) {
			// 判断优惠券的所有人
			Example couponUserExample = new Example(CouponMember.class);
			couponUserExample.createCriteria().andEqualTo("id", couponUserId).andEqualTo("couStatus", 1)
					.andEqualTo("userId", member.getId());
			List<CouponMember> couponUsers = couponMemberService.selectByExample(couponUserExample);
			if (null == couponUsers || couponUsers.size() == 0) {
				return ResponseBo.error("优惠券信息错误");
			}
			couponUser = couponUsers.get(0);
			if (couponUser.getCouStatus() > 1) {
				return ResponseBo.error("优惠券失效");
			}
			coupon = couponService.selectByKey(couponUser.getCouponId());

		}
		MemberAddress address = memberAddressService.selectByKey(addrId);
		if (null == address) {
			return ResponseBo.error("配送地址有误");
		}
		try {
			orderFacade.saveSkuOrder(order, coupon, member, address, items, couponUser);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error(e.getMessage());
		}
		// 操作成功清空购物车
		shoppingCartService.clearCart(request, response, member);
		return ResponseBo.ok(order);
	}

	@RequestMapping("/orderInfo")
	public String viewOrder(Model ui, Integer orderId) {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		try {
			MemberOrder order = this.orderService.selectByKey(orderId);
			if (null == order || !order.getBuyerId().equals(StpMemberUtil.getLoginIdAsInt())) {
				// 自己只能查看自己的订单详情
				return "404";
			}
			List<OrderItem> items = this.orderItemService.getOrderItems(orderId);
			Long invoiceId = order.getInvoiceId();
			OrderInvoice invoice = this.orderInvoiceService.selectByKey(invoiceId);
			List<OrderDelivery> orderDeliverys = orderDeliveryService.getDeliveryByOrderId(orderId);
			ui.addAttribute("order", order);
			ui.addAttribute("invoice", invoice);
			ui.addAttribute("items", items);
			ui.addAttribute("orderDeliverys", orderDeliverys);
			if (null != order.getCouponUserId()) {
				CouponMember couponUser = couponMemberService.selectByKey(order.getCouponUserId());
				ui.addAttribute("coupon", couponUser.getTitle());
			}
			return "blog/" + theme + "/order-info";
		} catch (Exception e) {
			log.error("获取订单信息失败", e);
			return "error/500";
		}
	}

	/**
	 * 确认收货后直接更新交易状态为交易结束(如果用户一直未操作，后台管理员确认签收后也可以直接改状态)
	 */
	@RequestMapping("/sureGetProducts")
	@ResponseBody
	public ResponseBo sureGetProducts(Long orderId) {
		if (null == orderId) {
			return ResponseBo.error();
		}
		MemberOrder order = orderService.selectByKey(orderId);
		if (order == null || order.getBuyerId().longValue() != StpMemberUtil.getLoginIdAsLong()) {
			return ResponseBo.error();
		}
		// 确认收货交易结束
		order.setTradeStatus(TradeStatus.trade_finish.getTradeStatus());
		order.setUpdateTime(new Date());
		String orderSummary = order.getOrderSummary();
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(orderSummary)) {
			sb.append(orderSummary);
		}
		String enumOrderStatusText = TradeStatus.getTradeEnumText(order.getTradeStatus());
		sb.append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "用户确认收货，状态变更为" + enumOrderStatusText);
		sb.append("\n <br/>");
		order.setOrderSummary(sb.toString());
		orderService.updateNotNull(order);
		return ResponseBo.ok();
	}

}
