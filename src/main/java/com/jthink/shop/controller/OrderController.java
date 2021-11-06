package com.jthink.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.member.enums.TradeStatus;
import com.jthink.permission.entity.JthinkUser;
import com.jthink.permission.service.UserService;
import com.jthink.shop.entity.CouponMember;
import com.jthink.shop.entity.MemberOrder;
import com.jthink.shop.entity.OrderDelivery;
import com.jthink.shop.entity.OrderInvoice;
import com.jthink.shop.entity.OrderItem;
import com.jthink.shop.service.CouponMemberService;
import com.jthink.shop.service.MemberOrderService;
import com.jthink.shop.service.OrderDeliveryService;
import com.jthink.shop.service.OrderInvoiceService;
import com.jthink.shop.service.OrderItemService;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/shop/order")
public class OrderController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberOrderService orderService;
	@Autowired
	private OrderItemService itemService;
	@Autowired
	private OrderInvoiceService orderInvoiceService;
	@Autowired
	private OrderDeliveryService orderDeliveryService;
	@Autowired
	private CouponMemberService couponMemberService;
	@Autowired
	private UserService userService;

	@RequestMapping({ "", "/index" })
	public String orderIndex() {
		return "shop/order/order";
	}

	@RequestMapping("/getOrder")
	@ResponseBody
	public ResponseBo getOrderById(Integer orderId) {
		MemberOrder order = orderService.selectByKey(orderId);
		Example example = new Example(OrderItem.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		List<OrderItem> items = itemService.selectByExample(example);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("order", order);
		res.put("items", items);
		return ResponseBo.ok(res);
	}

	@RequestMapping("/viewOrder")
	public String viewOrder(Model ui, Integer orderId) {
		try {
			MemberOrder order = this.orderService.selectByKey(orderId);
			Example example = new Example(OrderItem.class);
			example.createCriteria().andEqualTo("orderId", orderId);
			List<OrderItem> items = itemService.selectByExample(example);
			Long invoiceId = order.getInvoiceId();
			OrderInvoice invoice = this.orderInvoiceService.selectByKey(invoiceId);
			List<OrderDelivery> orderDeliverys = orderDeliveryService.getDeliveryByOrderId(orderId);
			ui.addAttribute("order", order);
			ui.addAttribute("invoice", invoice);
			ui.addAttribute("items", items);
			ui.addAttribute("orderDeliverys", orderDeliverys);
			if (null != order.getCouponUserId()) {
				CouponMember couponMember = couponMemberService.selectByKey(order.getCouponUserId());
				ui.addAttribute("coupon", couponMember.getTitle());
			}
			return "shop/order/orderView";
		} catch (Exception e) {
			log.error("获取订单信息失败", e);
			return "error/500";
		}
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> postList(QueryRequest request, MemberOrder order) {
		Example example = new Example(MemberOrder.class);
		Criteria criteria = example.createCriteria();
		if (null != order && StringUtils.isNotBlank(order.getOrderTitle())) {
			criteria.andLike("orderTitle", order.getOrderTitle());
		}
		if (null != order && StringUtils.isNotBlank(order.getOrderSummary())) {
			criteria.andLike("orderSummary", order.getOrderSummary());
		}
		Map<String, Object> orderMap = this.selectByPageNumSize(request, () -> orderService.selectByExample(example));
		return orderMap;

	}

	@RequestMapping("/updateOrderTradeStatus")
	@ResponseBody
	public ResponseBo updateOrderTradeStatus(MemberOrder order) {
		try {
			Integer orderId = order.getId();
			MemberOrder currentOrder = orderService.selectByKey(orderId);
			order.setUpdateTime(new Date());
			String orderSummary = currentOrder.getOrderSummary();
			StringBuffer sb = new StringBuffer();
			if (StringUtils.isNotBlank(orderSummary)) {
				sb.append(orderSummary);
			}
			if (order.getTradeStatus().intValue() == currentOrder.getTradeStatus().intValue()) {
				return ResponseBo.error("更新状态等于当前订单状态");
			}
			String enumOrderStatusText = TradeStatus.getTradeEnumText(order.getTradeStatus());
			sb.append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm") + "&nbsp;&nbsp;管理员更新订单状态为" + enumOrderStatusText);
			if (StringUtils.isNotEmpty(order.getOrderSummary())) {
				sb.append("备注:" + order.getOrderSummary());
			}
			sb.append("\n <br/>");
			order.setOrderSummary(sb.toString());
			this.orderService.updateNotNull(order);
			return ResponseBo.ok("修改订单成功！");
		} catch (Exception e) {
			log.error("修改订单失败", e);
			return ResponseBo.error("修改订单失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/deleteDeliver")
	@ResponseBody
	public ResponseBo deleteOrderDeliver(Long deliverId) {
		try {
			this.orderDeliveryService.delete(deliverId);
			return ResponseBo.ok("修改订单快递信息成功！");
		} catch (Exception e) {
			log.error("修改订单失败", e);
			return ResponseBo.error("修改订单失败，请联系网站管理员！");
		}
	}

	/**
	 * 查看快递信息
	 */
	@RequestMapping("/editDelivery")
	@ResponseBody
	public ResponseBo editOrderDelivery(Long deliveryId) {
		OrderDelivery orderDelivery = this.orderDeliveryService.selectByKey(deliveryId);
		return ResponseBo.ok(orderDelivery);
	}

	@RequestMapping("/updateDeliveryById")
	@ResponseBody
	public ResponseBo updateDeliveryById(OrderDelivery orderDelivery) {
		try {
			Integer userId = StpUtil.getLoginIdAsInt();
			JthinkUser user = userService.selectByKey(userId);
			orderDelivery.setUserId(user.getId());
			orderDelivery.setUsername(user.getUsername());

			this.orderDeliveryService.updateNotNull(orderDelivery);
			MemberOrder currentOrder = orderService.selectByKey(orderDelivery.getOrderId());
			String orderSummary = currentOrder.getOrderSummary();
			StringBuffer sb = new StringBuffer();
			if (StringUtils.isNotBlank(orderSummary)) {
				sb.append(orderSummary);
			}
			String enumOrderStatusText = TradeStatus.getTradeEnumText(currentOrder.getTradeStatus());
			sb.append(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm") + "&nbsp;&nbsp;管理员(" + user.getUsername() + ")更新快递状态为"
					+ enumOrderStatusText);
			sb.append("\n <br/>");
			currentOrder.setOrderSummary(sb.toString());
			orderService.updateNotNull(currentOrder);
			return ResponseBo.ok("修改订单快递信息成功！");
		} catch (Exception e) {
			log.error("修改订单失败", e);
			return ResponseBo.error("修改订单失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/updateDeliver")
	@ResponseBody
	public ResponseBo updateOrderDeliver(OrderDelivery orderDelivery) {
		try {
			Integer userId = StpUtil.getLoginIdAsInt();
			JthinkUser user = userService.selectByKey(userId);
			orderDelivery.setUserId(user.getId());
			orderDelivery.setUsername(user.getUsername());
			orderDelivery.setCreateTime(new Date());
			orderDelivery.setStartTime(new Date());

			this.orderDeliveryService.save(orderDelivery);
			return ResponseBo.ok();
		} catch (Exception e) {
			log.error("修改订单失败", e);
			return ResponseBo.error("修改订单失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/updateRemark")
	@ResponseBody
	public ResponseBo updateOrderRemarks(String orderRemarks, String orderId) {
		try {
			this.orderService.updateRemarks(orderRemarks, orderId);
			return ResponseBo.ok("修改订单成功！");
		} catch (Exception e) {
			log.error("修改订单失败", e);
			return ResponseBo.error("修改订单失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteOrders(String orderIds) {
		List<String> ids = Arrays.asList(orderIds.split(","));
		int counts = orderService.batchDelete(ids, "id", MemberOrder.class);
		Example example = new Example(OrderItem.class);
		example.createCriteria().andIn("orderId", ids);
		itemService.deleteByExample(example);
		return ResponseBo.ok("成功删除" + counts + "个订单");
	}
}
