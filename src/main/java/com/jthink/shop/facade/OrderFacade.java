package com.jthink.shop.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.member.entity.Member;
import com.jthink.shop.entity.CartItem;
import com.jthink.shop.entity.Coupon;
import com.jthink.shop.entity.CouponMember;
import com.jthink.shop.entity.MemberAddress;
import com.jthink.shop.entity.MemberOrder;
import com.jthink.shop.entity.OrderItem;
import com.jthink.shop.entity.ProductSku;
import com.jthink.shop.service.CouponMemberService;
import com.jthink.shop.service.MemberOrderService;
import com.jthink.shop.service.OrderItemService;
import com.jthink.shop.service.ProductSkuService;
import com.jthink.utils.OrderNumUtil;

import cn.hutool.core.date.DateUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OrderFacade {
	@Autowired
	private CouponMemberService couponMemberService;
	@Autowired
	private MemberOrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private PostService productService;
	@Autowired
	private ProductSkuService productSkuService;

	public int saveOrder(MemberOrder order, Coupon coupon, Member member, MemberAddress address, List<CartItem> items,
			CouponMember couponUser) throws Exception {
		// 生成订单
		String nsNumber = new OrderNumUtil(2, 3).nextId();
		order.setNs(nsNumber);
		order.setBuyerId(member.getId());
		order.setBuyerNickname(member.getUsername());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setPayStatus(1);
		order.setTradeStatus(1);
		order.setInvoiceStatus(1);
		order.setDeliveryAddrProvince(address.getProvince());
		order.setDeliveryAddrCity(address.getCity());
		order.setDeliveryAddrDistrict(address.getDistricts());
		order.setDeliveryAddrDetail(address.getAddrDetails());
		order.setDeliveryAddrZipcode(address.getZipcode());
		order.setDeliveryAddrUsername(address.getContactName());
		order.setDeliveryAddrMobile(address.getContactMobile());
		String summary = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm") +"&nbsp;&nbsp;"+ member.getUsername() + "&nbsp;&nbsp;生成订单 \n <br/>";
		order.setOrderSummary(summary);
		orderService.save(order);
		BigDecimal totalFee = new BigDecimal(0);
		StringBuffer buff = new StringBuffer();

		// 遍历购物车
		for (CartItem item : items) {
			Post product = productService.selectByKey(item.getProductId());
			if (null == product) {
				throw new Exception(item.getProductName() + "-购物车信息有误，请移除后重新下单");
			}

			if (null == product.getStocks() || item.getQuantity() > product.getStocks()) {
				throw new Exception(item.getProductName() + "-库存不足，请移除后重新下单");
			} else {
				// 减库存
				product.setStocks(product.getStocks() - item.getQuantity());
				productService.updateNotNull(product);
			}
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getId());
			orderItem.setOrderNs(nsNumber);
			orderItem.setProductTitle(item.getProductName());
			orderItem.setProductId(item.getProductId());
			orderItem.setBuyerId(member.getId());
			orderItem.setBuyerNickname(member.getUsername());
			orderItem.setProductCount(item.getQuantity());
			orderItem.setProductPrice(product.getPrice());
			orderItem.setProductType("product");
			orderItem.setProductTypeText("产品");

			BigDecimal itemFee = orderItem.getProductPrice().multiply(new BigDecimal(orderItem.getProductCount()));
			orderItem.setTotalAmount(itemFee.setScale(2, RoundingMode.HALF_UP));
			totalFee = totalFee.add(itemFee);
			buff.append(item.getProductName() + " ");
			orderItemService.save(orderItem);
			// 更新销量
			productService.updateAddSaleCount(item.getQuantity(), item.getProductId());
		}
		order.setOrderTitle(buff.toString());
		BigDecimal totalHalf = totalFee.setScale(2, RoundingMode.HALF_UP);
		// 运费计算(大于200名运费,默认10元运费)
		BigDecimal deliveryFee = new BigDecimal("10");
		if (totalHalf.compareTo(new BigDecimal(200)) == 1) {
			deliveryFee = new BigDecimal("0");
		}
		order.setOrderTotalAmount(totalHalf.add(deliveryFee));
		order.setCouponAmount(new BigDecimal(0));
		order.setDeliveryFee(deliveryFee);
		boolean hasMin = false;
		Integer type = coupon == null ? null : coupon.getType();
		if (null != type) {
			hasMin = type == 1 ? true : false;
		}

		if ((hasMin && totalHalf.compareTo(coupon.getWithAmount()) < 0)) {
			throw new Exception("优惠券金额未达到");
		}
		if (hasMin && totalHalf.compareTo(coupon.getWithAmount()) >= 0) {
			couponUser.setCouStatus(2);
			couponUser.setOrderNs(nsNumber);
			couponUser.setUsedOrderId(order.getId());
			couponUser.setUseTime(new Date());
			couponMemberService.updateNotNull(couponUser);
			order.setCouponUserId(couponUser.getId());
			order.setCouponAmount(coupon.getAmount());
			// 有门槛，判断是否符合门槛
			order.setOrderRealAmount(totalHalf.subtract(coupon.getAmount()).add(deliveryFee));
		} else if (hasMin == false && null != type) {
			couponUser.setCouStatus(2);
			couponUser.setOrderNs(nsNumber);
			couponUser.setUsedOrderId(order.getId());
			couponUser.setUseTime(new Date());
			couponMemberService.updateNotNull(couponUser);
			order.setCouponUserId(couponUser.getId());
			order.setCouponAmount(coupon.getAmount());
			BigDecimal realAmount = totalHalf.compareTo(coupon.getAmount()) < 0 ? new BigDecimal(0)
					: totalHalf.subtract(coupon.getAmount());
			// 无门槛，直接减
			order.setOrderRealAmount(realAmount.add(deliveryFee));
		} else {
			// 没券直接和总价相等
			order.setOrderRealAmount(totalHalf.add(deliveryFee));
		}
		orderService.updateNotNull(order);
		return 1;
	}

	public int saveSkuOrder(MemberOrder order, Coupon coupon, Member member, MemberAddress address,
			List<CartItem> items, CouponMember couponUser) throws Exception {
		// 生成订单
		String nsNumber = new OrderNumUtil(2, 3).nextId();
		order.setNs(nsNumber);
		order.setBuyerId(member.getId());
		order.setBuyerNickname(member.getUsername());
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		order.setPayStatus(1);
		order.setTradeStatus(1);
		order.setInvoiceStatus(1);
		order.setDeliveryAddrProvince(address.getProvince());
		order.setDeliveryAddrCity(address.getCity());
		order.setDeliveryAddrDistrict(address.getDistricts());
		order.setDeliveryAddrDetail(address.getAddrDetails());
		order.setDeliveryAddrZipcode(address.getZipcode());
		order.setDeliveryAddrUsername(address.getContactName());
		order.setDeliveryAddrMobile(address.getContactMobile());
		String summary = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm") + "&nbsp;&nbsp;"+member.getUsername() + "&nbsp;&nbsp;生成订单 \n <br/>";
		order.setOrderSummary(summary);
		orderService.save(order);
		BigDecimal totalFee = new BigDecimal(0);
		StringBuffer buff = new StringBuffer();

		// 遍历购物车
		for (CartItem item : items) {
			Post product = productService.selectByKey(item.getProductId());
			ProductSku sku = productSkuService.getSku(item.getProductId(), item.getSpuvalIds());
			if (null == sku) {
				throw new Exception("商品规格有误");
			}
			if (null == product) {
				throw new Exception(item.getProductName() + "-购物车信息有误，请移除后重新下单");
			}

			if (null == sku.getStock() || sku.getStock() <= 0 || item.getQuantity() > sku.getStock()) {
				throw new Exception(item.getProductName() + "-库存不足，请移除后重新下单");
			} else {
				// 减库存
				productSkuService.updateStock(sku.getId(), sku.getStock() - item.getQuantity());
			}
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getId());
			orderItem.setOrderNs(nsNumber);
			orderItem.setProductTitle(item.getProductName());
			orderItem.setProductId(item.getProductId());
			orderItem.setBuyerId(member.getId());
			orderItem.setBuyerNickname(member.getUsername());
			orderItem.setProductCount(item.getQuantity());
			orderItem.setProductPrice(product.getPrice());
			orderItem.setProductType("product");
			orderItem.setProductTypeText("产品");

			BigDecimal itemFee = orderItem.getProductPrice().multiply(new BigDecimal(orderItem.getProductCount()));
			orderItem.setTotalAmount(itemFee.setScale(2, RoundingMode.HALF_UP));
			totalFee = totalFee.add(itemFee);
			buff.append(item.getProductName() + " ");
			orderItemService.save(orderItem);
			// 更新销量
			productService.updateAddSaleCount(item.getQuantity(), item.getProductId());
		}
		order.setOrderTitle(buff.toString());
		BigDecimal totalHalf = totalFee.setScale(2, RoundingMode.HALF_UP);
		// 运费计算(大于200名运费,默认10元运费)
		BigDecimal deliveryFee = new BigDecimal("10");
		if (totalHalf.compareTo(new BigDecimal(200)) == 1) {
			deliveryFee = new BigDecimal("0");
		}
		order.setOrderTotalAmount(totalHalf.add(deliveryFee));
		order.setCouponAmount(new BigDecimal(0));
		order.setDeliveryFee(deliveryFee);
		boolean hasMin = false;
		Integer type = coupon == null ? null : coupon.getType();
		if (null != type) {
			hasMin = type == 1 ? true : false;
		}

		if ((hasMin && totalHalf.compareTo(coupon.getWithAmount()) < 0)) {
			throw new Exception("优惠券金额未达到");
		}
		if (hasMin && totalHalf.compareTo(coupon.getWithAmount()) >= 0) {
			couponUser.setCouStatus(2);
			couponUser.setOrderNs(nsNumber);
			couponUser.setUsedOrderId(order.getId());
			couponUser.setUseTime(new Date());
			couponMemberService.updateNotNull(couponUser);
			order.setCouponUserId(couponUser.getId());
			order.setCouponAmount(coupon.getAmount());
			// 有门槛，判断是否符合门槛
			order.setOrderRealAmount(totalHalf.subtract(coupon.getAmount()).add(deliveryFee));
		} else if (hasMin == false && null != type) {
			couponUser.setCouStatus(2);
			couponUser.setOrderNs(nsNumber);
			couponUser.setUsedOrderId(order.getId());
			couponUser.setUseTime(new Date());
			couponMemberService.updateNotNull(couponUser);
			order.setCouponUserId(couponUser.getId());
			order.setCouponAmount(coupon.getAmount());
			BigDecimal realAmount = totalHalf.compareTo(coupon.getAmount()) < 0 ? new BigDecimal(0)
					: totalHalf.subtract(coupon.getAmount());
			// 无门槛，直接减
			order.setOrderRealAmount(realAmount.add(deliveryFee));
		} else {
			// 没券直接和总价相等
			order.setOrderRealAmount(totalHalf.add(deliveryFee));
		}
		orderService.updateNotNull(order);
		return 1;
	}
}
