package com.jthink.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.QueryRequest;
import com.jthink.member.controller.MemberBaseController;
import com.jthink.member.entity.Member;
import com.jthink.permission.service.UserService;
import com.jthink.shop.entity.Coupon;
import com.jthink.shop.entity.CouponMember;
import com.jthink.shop.service.CouponMemberService;
import com.jthink.shop.service.CouponService;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.stp.StpUtil;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/shop/coupon")
public class CouponController extends MemberBaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CouponService couponService;
	@Autowired
	private CouponMemberService couponMemberService;
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;

	@RequestMapping({"","/index"})
	public String index() {
		return "/shop/coupon/coupon";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> couponList(QueryRequest request) {
		return this.selectByPageNumSize(request, () -> couponService.selectAll());
	}

	@RequestMapping("/getCoupon")
	@ResponseBody
	public ResponseBo getCoupon(Long couponId) {
		try {
			Coupon coupon = couponService.selectByKey(couponId);
			return ResponseBo.ok(coupon);
		} catch (Exception e) {
			log.error("获取优惠券信息失败", e);
			return ResponseBo.error("获取优惠券信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addCoupon(Coupon coupon) {
		coupon.setCreateTime(new Date());
		coupon.setUpdateTime(new Date());
		coupon.setCouStatus("1");
		coupon.setUsedCount(0);
		coupon.setTakeCount(0);
		Integer userId = StpUtil.getLoginIdAsInt();
		coupon.setCreateUserId(userId);
		couponService.save(coupon);
		return ResponseBo.ok("添加优惠券成功");
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updateCoupon(Coupon coupon) {
		coupon.setUpdateTime(new Date());
		couponService.updateNotNull(coupon);
		return ResponseBo.ok("更新优惠券成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteCoupons(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			this.couponService.batchDelete(list, "id", Coupon.class);
			return ResponseBo.ok("删除优惠券类型成功！");
		} catch (Exception e) {
			log.error("删除优惠券类型失败", e);
			return ResponseBo.error("删除优惠券类型失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/couponUser")
	public String couponUser(HttpServletRequest request, Long couponId) {
		Coupon coupon = couponService.selectByKey(couponId);
		request.setAttribute("coupon", coupon);
		return "/shop/coupon/couponUser";
	}

	@RequestMapping("/couponUser/list")
	@ResponseBody
	public Map<String, Object> couponUserList(QueryRequest request, Long couponId) {
		Example example = new Example(CouponMember.class);
		example.createCriteria().andEqualTo("couponId", couponId);
		return this.selectByPageNumSize(request, () -> couponMemberService.selectByExample(example));
	}

	/**
	 * 给指定用户发放优惠券
	 */
	@RequestMapping("/couponUser/send")
	@ResponseBody
	public ResponseBo sendCouponUser(Integer couponId, String ids) {
		Coupon coupon = couponService.selectByKey(couponId);
		if (coupon.getCouStatus().equals("0")) {
			log.error(couponId + "优惠券已锁定");
			return ResponseBo.error("领取失败");
		}
		if (coupon.getTakeCount() == coupon.getQuota()) {
			log.error(couponId + "优惠券已领完");
			return ResponseBo.error("领取失败");
		}
		List<String> list = Arrays.asList(ids.split(","));
		Integer userId = StpUtil.getLoginIdAsInt();
		for (String idStr : list) {
			Member reciever=memberService.selectByKey(idStr);
			CouponMember couponUser = new CouponMember();
			couponUser.setTitle(coupon.getTitle());
			couponUser.setSendUid(userId);
			couponUser.setUserId(reciever.getId());
			couponUser.setUserName(reciever.getUsername());
			couponUser.setCreateTime(new Date());
			couponUser.setCouStatus(1);
			couponUser.setCouponId(couponId);
			couponUser.setGetTime(new Date());
			couponMemberService.save(couponUser);
		}
		return ResponseBo.ok("发放优惠券成功");
	}

	@RequestMapping("/couponUser/delete")
	@ResponseBody
	public ResponseBo deleteCouponUsers(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			this.couponMemberService.batchDelete(list, "id", CouponMember.class);
			return ResponseBo.ok("删除优惠券成功！");
		} catch (Exception e) {
			log.error("删除优惠券失败", e);
			return ResponseBo.error("删除优惠券失败，请联系网站管理员！");
		}
	}
}
