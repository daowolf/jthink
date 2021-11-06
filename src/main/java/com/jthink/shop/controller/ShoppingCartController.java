package com.jthink.shop.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.member.entity.Member;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.entity.CartItem;
import com.jthink.shop.entity.ProductSku;
import com.jthink.shop.entity.ShoppingCart;
import com.jthink.shop.service.MemberService;
import com.jthink.shop.service.ProductSkuService;
import com.jthink.shop.service.ShoppingCartService;
import com.jthink.utils.ResponseBo;

@Controller
@RequestMapping("/shopcart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService service;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PostService postService;
	@Autowired
	private ProductSkuService productSkuService;

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping("/cartList")
	@ResponseBody
	public ResponseBo cartList(HttpServletRequest req, HttpServletResponse resp) {
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer mid = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(mid);
		}
		String key = service.getKey(req, resp, member);
		ShoppingCart cacheCart = service.mergeCart(key, member);
		Map<String, Object> data = new HashMap<>();
		List<CartItem> items = cacheCart.getCartItems();
		data.put("items", items);
		BigDecimal total = new BigDecimal("0");
		for (CartItem item : items) {
			BigDecimal count = new BigDecimal(item.getQuantity());
			BigDecimal itemAllPrice = item.getPrice().multiply(count);
			total = total.add(itemAllPrice);
		}
		// 运费计算(大于200名运费,默认10元运费)
		BigDecimal deliveryFee = new BigDecimal("10");
		if (total.compareTo(new BigDecimal(200)) == 1) {
			deliveryFee = new BigDecimal("0");
		}
		data.put("productMoney", total.setScale(2, BigDecimal.ROUND_HALF_UP));
		data.put("total", total.setScale(2, BigDecimal.ROUND_HALF_UP).add(deliveryFee));
		data.put("deliveryFee", deliveryFee);

		return ResponseBo.ok(data);
	}

	/**
	 * 
	 * 普通规格
	 */
	@PostMapping("/addCart")
	@ResponseBody
	public ResponseBo add(HttpServletRequest req, HttpServletResponse resp) {
		if (null == req.getParameter("productId")) {
			return ResponseBo.error("商品参数信息有误");
		}
		String productId = req.getParameter("productId");
		Post product = postService.selectByKey(productId);
		if (null == product) {
			return ResponseBo.error("商品信息有误，请重新选择");
		}
		if (null == product.getPrice()) {
			return ResponseBo.error("商品价格有误,请重新选择");
		}
		CartItem cartItem = new CartItem();
		cartItem.setProductId(product.getId());
		cartItem.setProductName(product.getPostTitle());
		cartItem.setPrice(product.getPrice());
		cartItem.setImgUrl(product.getImgPreview());
		cartItem.setQuantity(1);
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer mid = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(mid);
		}
		ResponseBo result = service.addCart(req, resp, member, cartItem);
		return result;
	}

	/***
	 * 
	 * sku规格商品添加
	 */
	@PostMapping("/addSkuCart")
	@ResponseBody
	public ResponseBo addSku(HttpServletRequest req, HttpServletResponse resp) {
		if (null == req.getParameter("productId") || null == req.getParameter("spuIds")) {
			return ResponseBo.error("请选择正确的商品规格");
		}
		String productId = req.getParameter("productId");
		String spuIds = req.getParameter("spuIds");
		Post product = postService.selectByKey(productId);
		if (null == product) {
			return ResponseBo.error("商品信息有误，请重新选择");
		}
		ProductSku sku = productSkuService.getSku(productId, spuIds);
		if (null == sku) {
			return ResponseBo.error("商品参数信息有误");
		}
		if (sku.getStock() <= 0) {
			return ResponseBo.error("商品库存不足,请重新选择");
		}
		if (null == sku.getPrice()) {
			return ResponseBo.error("商品价格有误,请重新选择");
		}
		CartItem cartItem = new CartItem();
		cartItem.setProductId(product.getId());
		cartItem.setProductName(product.getPostTitle());
		cartItem.setPrice(sku.getPrice());
		cartItem.setSpuvalIds(sku.getSpuvalIds());
		cartItem.setSpuvalStr(sku.getSpuvalStr());
		cartItem.setImgUrl(product.getImgPreview());
		cartItem.setQuantity(1);
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer mid = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(mid);
		}
		ResponseBo result = service.addCart(req, resp, member, cartItem);
		return result;
	}

	@PostMapping("/removeCart")
	@ResponseBody
	public ResponseBo remove(HttpServletRequest req, HttpServletResponse resp, CartItem cartItem) {
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer mid = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(mid);
		}
		ResponseBo result = service.removeCart(req, resp, member, cartItem);
		return result;
	}

	@PostMapping("/resetCart")
	@ResponseBody
	public ResponseBo resetCart(HttpServletRequest req, HttpServletResponse resp, CartItem cartItem) {
		Member member = null;
		if (StpMemberUtil.isLogin()) {
			Integer mid = StpMemberUtil.getLoginIdAsInt();
			member = memberService.selectByKey(mid);
		}
		ResponseBo result = service.resetCart(req, resp, member, cartItem);
		return result;
	}
}
