package com.jthink.member.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.member.entity.Member;
import com.jthink.security.util.LoginUtil;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.entity.MemberFavourite;
import com.jthink.shop.service.MemberFavouriteService;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/member")
public class MemberController extends MemberBaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;
	@Autowired
	private MemberFavouriteService memberFavouriteService;
	@Autowired
	private PostService postService;
	@Autowired
	private MemberService memberService;

	@RequestMapping("/shopcart")
	public String shopCart() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/shop-cart";
	}

	@RequestMapping("/myaccount")
	public String myAccount(Model ui) {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/my-account";
	}

	@RequestMapping("/checkout")
	public String checkout() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/checkout";
	}

	@RequestMapping("/favourite/index")
	public String favoriteIndex() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/wishlist";
	}

	@RequestMapping("/favourite/list")
	@ResponseBody
	public Map<String, Object> favouriteList(QueryRequest request) {

		Example favouriteExample = new Example(MemberFavourite.class);
		favouriteExample.createCriteria().andEqualTo("userId", StpMemberUtil.getLoginIdAsInt());
		return super.selectByPageNumSize(request, () -> memberFavouriteService.selectByExample(favouriteExample));
	}

	/**
	 * ??????????????????
	 * 
	 * @param articleId ??????ID
	 */
	@RequestMapping("/favourite/addArticleFavourite")
	@ResponseBody
	public ResponseBo addArticleFavourite(Integer articleId) {
		if (!StpMemberUtil.isLogin()) {
			ResponseBo.error("?????????");
		}
		Post article = postService.selectByKey(articleId);
		if (null == article) {
			ResponseBo.error("???????????????");
		}
		// ????????????????????????
		Example favouriteExample = new Example(MemberFavourite.class);
		favouriteExample.createCriteria().andEqualTo("userId", StpMemberUtil.getLoginIdAsInt())
				.andEqualTo("type", "post").andEqualTo("favouriteId", articleId);
		List<MemberFavourite> userFavourites = memberFavouriteService.selectByExample(favouriteExample);
		if (null != userFavourites && userFavourites.size() > 0) {
			return ResponseBo.ok();
		}
		MemberFavourite memberFavourite = new MemberFavourite();
		memberFavourite.setType("post");
		memberFavourite.setFavouriteId(articleId);
		memberFavourite.setTitle(article.getPostTitle());
		memberFavourite.setCreateTime(new Date());
		memberFavourite.setUserId(StpMemberUtil.getLoginIdAsInt());
		memberFavouriteService.save(memberFavourite);
		return ResponseBo.ok("????????????");
	}

	/**
	 * ??????????????????
	 * 
	 * @param productId ??????ID
	 */
	@RequestMapping("/favourite/addProductFavourite")
	@ResponseBody
	public ResponseBo addProductFavourite(Integer productId) {
		if (!StpMemberUtil.isLogin()) {
			return ResponseBo.error("?????????");
		}
		Post product = postService.selectByKey(productId);
		if (null == product) {
			return ResponseBo.error("???????????????");
		}
		Integer userId = StpMemberUtil.getLoginIdAsInt();
		// ????????????????????????
		Example favouriteExample = new Example(MemberFavourite.class);
		favouriteExample.createCriteria().andEqualTo("userId", userId).andEqualTo("type", "product")
				.andEqualTo("favouriteId", productId);
		List<MemberFavourite> userFavourites = memberFavouriteService.selectByExample(favouriteExample);
		if (null != userFavourites && userFavourites.size() > 0) {
			return ResponseBo.ok();
		}
		MemberFavourite memberFavourite = new MemberFavourite();
		memberFavourite.setType("product");
		memberFavourite.setFavouriteId(productId);
		memberFavourite.setTitle(product.getPostTitle());
		memberFavourite.setCreateTime(new Date());
		memberFavourite.setUserId(userId);
		memberFavouriteService.save(memberFavourite);
		return ResponseBo.ok("????????????");
	}

	/**
	 * ????????????
	 * 
	 * @param type post/product
	 */
	@RequestMapping("/favourite/cancelFavourite")
	@ResponseBody
	public ResponseBo cancelFavorite(Long favoriteId) {
		Example favouriteExample = new Example(MemberFavourite.class);
		favouriteExample.createCriteria().andEqualTo("favouriteId", favoriteId);
		memberFavouriteService.deleteByExample(favouriteExample);
		return ResponseBo.ok("?????????");
	}

	@GetMapping("/updatepwd")
	public String toUpdatePwd() {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/update-password";
	}

	/**
	 * ????????????
	 */
	@PostMapping("/updatepwd")
	public String updatePwd(Model ui, String oldPwd, String newPwd, String newConfirm) {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		if (null == newConfirm || null == newPwd || !newConfirm.equals(newPwd)) {
			ui.addAttribute("msg", "???????????????????????????");
			return "blog/" + theme + "/update-password";
		}

		Integer memberId = StpMemberUtil.getLoginIdAsInt();
		Member member = memberService.selectByKey(memberId);
		if (LoginUtil.matchPass(oldPwd, member.getPassword())) {
			String newPassword = LoginUtil.encodePwd(newPwd);
			member.setPassword(newPassword);
			memberService.updateNotNull(member);
			return "redirect:/member/logout";
		} else {
			ui.addAttribute("msg", "??????????????????");
			return "blog/" + theme + "/update-password";
		}

	}
	@PostMapping("/updateInfo")
	public String updateInfo(Model ui,Member member) {
		memberService.updateNotNull(member);
		ui.addAttribute("msg", "????????????");
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/my-account";
	}

	@RequestMapping("/favourite/delete")
	@ResponseBody
	public ResponseBo deleteCollects(String ids) {
		try {
			List<String> list = Arrays.asList(ids.split(","));
			this.memberFavouriteService.batchDelete(list, "id", MemberFavourite.class);
			return ResponseBo.ok("?????????????????????");
		} catch (Exception e) {
			log.error("??????????????????", e);
			return ResponseBo.error("????????????????????????????????????????????????");
		}
	}
}
