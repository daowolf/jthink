package com.jthink.shop.service;

import java.math.BigDecimal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.jthink.cms.service.PostService;
import com.jthink.common.config.cache.JthinkCache;
import com.jthink.member.entity.Member;
import com.jthink.shop.entity.CartItem;
import com.jthink.shop.entity.ShoppingCart;
import com.jthink.utils.ResponseBo;

import cn.hutool.core.lang.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ShoppingCartService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService postService;
	@Autowired
	private JthinkCache jthinkCache;

	/**
	 * 获得用户key
	 *
	 * 1.用户未登录情况下第一次进入购物车 -> 生成key 保存至cookie中 2.用户未登录情况下第n进入购物车 -> 从cookie中取出key
	 * 3.用户登录情况下 -> 根据用户code生成key 4.用户登录情况下并且cookie中存在key-> 从cookie取的的key从缓存取得购物车
	 * 合并至 用户code生成key的购物车中去 ，这样后面才能根据用户code 取得正确的购物车
	 *
	 * @param req
	 * @param resp
	 * @param account
	 * @return
	 */

	public String getKey(HttpServletRequest req, HttpServletResponse resp, Member member) {
		String key = null; // 最终返回的key
		String tempKey = ""; // 用来存储cookie中的临时key,

		Cookie cartCookie = WebUtils.getCookie(req, "shoopingCart");
		if (cartCookie != null) {
			// 获取Cookie中的key
			key = cartCookie.getValue();
			tempKey = cartCookie.getValue();
		}
		if (StringUtils.isBlank(key)) {
			key = ShoppingCart.unLoginKeyPrefix + UUID.randomUUID();
			if (member != null)
				key = ShoppingCart.loginKeyPrefix + member.getUsername();
			Cookie cookie = new Cookie("shoopingCart", key);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else if (StringUtils.isNotBlank(key) && member != null) {// ⑵
			key = ShoppingCart.loginKeyPrefix + member.getUsername();
			if (tempKey.startsWith(ShoppingCart.unLoginKeyPrefix)) {// ⑴
				// 1.满足cookie中取得的key 为未登录时的key
				// 2.满足当前用户已经登录
				// 3.合并未登录时用户所添加的购物车商品⑷
				mergeCart(tempKey, member);// ⑶
			}
		}
		return key;
	}

	public ShoppingCart mergeCart(String tempKey, Member member) {
		ShoppingCart loginCart = null;
		String loginkey = null;

		// 从redis取出用户缓存购物车数据
		Gson gson = new Gson();
		String unLoginCartJson = jthinkCache.getKey(tempKey);
		ShoppingCart unLoginCart;
		if (unLoginCartJson == null) {
			unLoginCart = new ShoppingCart(tempKey);
		} else {
			unLoginCart = gson.fromJson(unLoginCartJson, ShoppingCart.class);
		}
		if (member != null && tempKey.startsWith(ShoppingCart.unLoginKeyPrefix)) {// ⑵
			// 如果用户登录 并且 当前是未登录的key
			loginkey = ShoppingCart.loginKeyPrefix + member.getUsername();
			loginCart = mergeCart(loginkey, member);
			if (null != unLoginCart.getCartItems()) {// ⑴

				if (null != loginCart.getCartItems()) {
					// 满足未登录时的购物车不为空 并且 当前用户已经登录
					// 进行购物车合并
					for (CartItem cv : unLoginCart.getCartItems()) {
						long count = loginCart.getCartItems().stream()
								.filter(it -> it.getProductId().equals(cv.getProductId())).count();
						if (count == 0) {// 没有重复的商品 则直接将商品加入购物车
							loginCart.getCartItems().add(cv);
						} else if (count == 1) {// 出现重复商品 修改数量
							CartItem c = loginCart.getCartItems().stream()
									.filter(it -> it.getProductId().equals(cv.getProductId())).findFirst().orElse(null);
							c.setQuantity(c.getQuantity() + 1);
							BigDecimal allCt = new BigDecimal(c.getQuantity());
							c.setAllPrice(c.getPrice().multiply(allCt));
						}
					}
				} else {
					// 如果当前登录用户的购物车为空则 将未登录时的购物车合并
					loginCart.setCartItems(unLoginCart.getCartItems());
				}
				unLoginCart = loginCart;
				// 【删除临时key】
				jthinkCache.removeItem(tempKey);
				// 默认一小时有效期【将合并后的购物车数据 放入loginKey】//TMP_4369f86d-c026-4b1b-8fec-f3c69f6ffac5
				jthinkCache.set(loginkey, gson.toJson(unLoginCart), 60 * 60);

			}
		}
		return unLoginCart;
	}

	/**
	 * 添加购物车
	 * 
	 * @param req
	 * @param resp
	 * @param member 登陆用户信息
	 * @param item   添加的购物车商品信息 包含商品code 商品加购数量
	 * @return
	 */

	public ResponseBo addCart(HttpServletRequest req, HttpServletResponse resp, Member member, CartItem item) {
		String key = getKey(req, resp, member);// 得到最终key
		ShoppingCart cacheCart = mergeCart(key, member);// 根据key取得最终购物车对象
		if (null != item.getProductId() && item.getQuantity() > 0) {
			long count = 0;
			if (null != cacheCart.getCartItems()) {
				count = cacheCart.getCartItems().stream().filter(it -> it.getProductId().equals(item.getProductId()))
						.count();
			}
			if (count == 0) {
				// 之前购物车无该商品记录 则直接添加
				item.setAllPrice(item.getPrice());
				cacheCart.getCartItems().add(item);

			} else {
				// 否则将同一商品数量相加
				CartItem c = cacheCart.getCartItems().stream()
						.filter(it -> it.getProductId().equals(item.getProductId())).findFirst().orElse(null);
				c.setQuantity(c.getQuantity() + item.getQuantity());
				BigDecimal allCt = new BigDecimal(c.getQuantity());
				c.setAllPrice(c.getPrice().multiply(allCt));
			}
		}
		// 【将合并后的购物车数据 放入loginKey】
		Gson gson = new Gson();
		jthinkCache.set(key, gson.toJson(cacheCart), 60 * 60);
		return ResponseBo.ok(cacheCart);
	}

	/**
	 * 清空购物车
	 */
	public ResponseBo clearCart(HttpServletRequest req, HttpServletResponse resp, Member member) {
		String key = getKey(req, resp, member);// 得到最终key
		jthinkCache.removeItem(key);
		// 删除cookie
		Cookie cookie = new Cookie("shoopingCart", key);
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		resp.addCookie(cookie);
		return ResponseBo.ok();
	}

	/**
	 * 移除购物车
	 * 
	 * @param req
	 * @param resp
	 * @param account
	 * @param item
	 * @return
	 */

	public ResponseBo removeCart(HttpServletRequest req, HttpServletResponse resp, Member member, CartItem item) {
		// 1.满足缓存购物车中必须有商品才能减购物车
		// 2.满足缓存购物车中有该商品才能减购物车
		// 3.判断此次要减数量是否大于缓存购物车中数量 进行移除还是数量相减操作

		String key = getKey(req, resp, member);// 得到最终key
		ShoppingCart cacheCart = mergeCart(key, member);// 根据key取得最终购物车对象
		if (cacheCart != null && cacheCart.getCartItems() != null && cacheCart.getCartItems().size() > 0) {// ⑴
			//
			CartItem ci = cacheCart.getCartItems().stream().filter(it -> it.getProductId().equals(item.getProductId()))
					.findFirst().orElse(null);
			if (null != ci) {
				if (ci.getQuantity() > item.getQuantity()) {
					ci.setQuantity(ci.getQuantity() - item.getQuantity());
					BigDecimal allCt = new BigDecimal(ci.getQuantity());
					ci.setAllPrice(ci.getPrice().multiply(allCt));
				} else if (ci.getQuantity() < item.getQuantity()) {
					BigDecimal allCt = new BigDecimal(item.getQuantity());
					ci.setQuantity(item.getQuantity());
					ci.setAllPrice(ci.getPrice().multiply(allCt));
				} else if (ci.getQuantity().equals(item.getQuantity())) {
					cacheCart.getCartItems().remove(ci);
				}
			}

			Gson gson = new Gson();
			jthinkCache.set(key, gson.toJson(cacheCart), 1 * 60);
		}
		return ResponseBo.ok(cacheCart);
	}

	public ResponseBo resetCart(HttpServletRequest req, HttpServletResponse resp, Member member, CartItem item) {
		// 1.满足缓存购物车中必须有商品才能减购物车
		// 2.满足缓存购物车中有该商品才能减购物车
		// 3.判断此次要减数量是否大于缓存购物车中数量 进行移除还是数量相减操作

		String key = getKey(req, resp, member);// 得到最终key
		ShoppingCart cacheCart = mergeCart(key, member);// 根据key取得最终购物车对象
		if (cacheCart != null && cacheCart.getCartItems() != null && cacheCart.getCartItems().size() > 0) {// ⑴
			//
			CartItem ci = cacheCart.getCartItems().stream().filter(it -> it.getProductId().equals(item.getProductId()))
					.findFirst().orElse(null);
			if (null != ci) {
				if (item.getQuantity() > 0) {
					BigDecimal allCt = new BigDecimal(item.getQuantity());
					ci.setQuantity(item.getQuantity());
					ci.setAllPrice(ci.getPrice().multiply(allCt));
				} else {
					cacheCart.getCartItems().remove(ci);
				}
			}

			Gson gson = new Gson();
			jthinkCache.set(key, gson.toJson(cacheCart), 1 * 60);
		}
		return ResponseBo.ok(cacheCart);
	}
}
