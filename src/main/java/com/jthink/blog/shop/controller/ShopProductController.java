package com.jthink.blog.shop.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.CommentState;
import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.JthinkImage;
import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.PostComment;
import com.jthink.cms.entity.PostMeta;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.JthinkImageService;
import com.jthink.cms.service.PostCommentService;
import com.jthink.cms.service.PostImageService;
import com.jthink.cms.service.PostMetaService;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.cms.service.TermService;
import com.jthink.directive.ThymeleafVariUtil;
import com.jthink.member.entity.Member;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.service.MemberService;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
public class ShopProductController {
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService postService;
	@Autowired
	private TermRelationshipService termRelationshipService;
	@Autowired
	private TermService termService;
	@Autowired
	private PostMetaService postMetaService;
	@Autowired
	private JthinkImageService jthinkImageService;
	@Autowired
	private PostImageService postImageService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PostCommentService postCommentService;

	@RequestMapping("/productinfo/{productId}")
	public String index(@PathVariable(name = "productId", required = false) Integer productId, String navId,
			Model model) {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		Post post = postService.selectByKey(productId);
		if (null == post) {
			return "404";
		}
		List<Term> categoryList = termService.queryPostTermsByTaxon(Taxonomy.productCategory, productId);
		List<Integer> taxids = categoryList.stream().map(Term::getId).collect(Collectors.toList());
		Example metaExample = new Example(PostMeta.class);
		metaExample.createCriteria().andEqualTo("postId", productId);
		List<PostMeta> metas = postMetaService.selectByExample(metaExample);

		Map<String, Object> data = new HashMap<String, Object>();
		if (null != metas && metas.size() > 0) {
			Map<String, String> mapMetas = metas.stream()
					.collect(Collectors.toMap(PostMeta::getMetaKey, PostMeta::getMetaValue));
			data.put("metas", mapMetas);
		}
		// 图片列表
		List<JthinkImage> imgs = jthinkImageService.queryPostImages(productId);
		model.addAttribute("post", post);
		model.addAttribute("taxids", taxids);
		model.addAttribute("imgs", imgs);
		Post navPost;
		// 面包屑
		List<Post> breadcrumb = new ArrayList<Post>();
		if (null != navId) {
			navPost = postService.selectByKey(navId);
			model.addAttribute("navPost", navPost);
			model.addAttribute("navId", navPost.getId());
			this.setNavList(breadcrumb, navId);
			Collections.reverse(breadcrumb);
		}
		model.addAttribute("breadcrumb", breadcrumb);
		// sku spu 相关参数

		return "blog/" + theme + "/product";
	}
	@RequestMapping("/productBlock/{categoryId}")
	public String productBlock(@PathVariable("categoryId") Integer categoryId,Model ui) {
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		ui.addAttribute("categoryId", categoryId);
		return "blog/" + theme + "/productBlock"; 
	}
	public void setNavList(List<Post> lists, Serializable navId) {
		Post navPost = postService.selectByKey(navId);
		lists.add(navPost);
		if (null != navPost.getPostParent() && navPost.getPostParent() > 0) {
			this.setNavList(lists, navPost.getPostParent());
		}
	}

	@RequestMapping("/member/product/sendComment")
	@ResponseBody
	public ResponseBo sendComment(PostComment comment) {
		comment.setCreateTime(new Date());
		comment.setCommentApproved(CommentState.AUDIT);
		Integer memberId = StpMemberUtil.getLoginIdAsInt();
		Member member = memberService.selectByKey(memberId);
		comment.setCommentAuthor(member.getNickname());
		comment.setUserId(memberId);
		// 审核中
		comment.setCommentApproved(CommentState.AUDIT);
		postCommentService.save(comment);
		return ResponseBo.ok();
	}
}
