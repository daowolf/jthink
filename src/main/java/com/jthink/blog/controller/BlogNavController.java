package com.jthink.blog.controller;

import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.PostMeta;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.PostMetaService;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermService;
import com.jthink.common.service.JoptionService;
import com.jthink.directive.ThymeleafVariUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 导航控制器前台系统入口，所有的文章、页面、文章列表都要通过导航进入跳转定位
 */
@Controller
public class BlogNavController {
	@Autowired
	private PostService postService;
	@Autowired
	private TermService termService;
	@Autowired
	private JoptionService joptionService;
	@Autowired
	private PostMetaService postMetaService;
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	/**
	 * 页面跳转
	 */
	@RequestMapping({ "/page/{navId}" })
	public String pageNav(@PathVariable(name = "navId", required = false) String navId, Model model) {
		Post navPost;
		if (NumberUtils.isDigits(navId)) {
			navPost = postService.selectByKey(navId);
		} else {
			Example example = new Example(Post.class);
			example.createCriteria().andEqualTo("postName", navId);
			List<Post> posts = postService.selectByExample(example);
			navPost = posts.size() == 0 ? null : posts.get(0);
		}
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		if (null == navPost) {
			return "404";
		}
		// 面包屑
		List<Post> breadcrumb = new ArrayList<Post>();
		if (null!=navPost) {
			model.addAttribute("navPost", navPost);
			model.addAttribute("navId", navPost.getId());
			this.setNavList(breadcrumb, navId);
			Collections.reverse(breadcrumb);
		} 
		model.addAttribute("breadcrumb", breadcrumb);
		String pageId = navPost.getPostContent();
		Post pagePost = postService.selectByKey(pageId);
		model.addAttribute("pagePost", pagePost);

		return "blog/" + theme + "/" + pagePost.getTemplate();
	}

	/**
	 * 通过导文章列表航访问指定的postId的文章
	 */
	@RequestMapping("/post/{postId}")
	public String postNav(@PathVariable(name = "postId") String postId, String navId,
			Model model, HttpServletRequest request) {
		Post post;
		// 文章
		if (NumberUtils.isDigits(postId)) {
			post = postService.selectByKey(postId);
		} else {
			Example example = new Example(Post.class);
			example.createCriteria().andEqualTo("postName", postId);
			List<Post> posts = postService.selectByExample(example);
			post = posts.size() == 0 ? null : posts.get(0);
		}
		Post navPost;
		// 面包屑
		List<Post> breadcrumb = new ArrayList<Post>();
		if (null!=navId) {
			navPost = postService.selectByKey(navId);
			model.addAttribute("navPost", navPost);
			model.addAttribute("navId", navPost.getId());
			this.setNavList(breadcrumb, navId);
			Collections.reverse(breadcrumb);
		} 
		model.addAttribute("breadcrumb", breadcrumb);
		// 文章扩展信息
		Example metaExample = new Example(PostMeta.class);
		metaExample.createCriteria().andEqualTo("postId", postId);
		List<PostMeta> metas = postMetaService.selectByExample(metaExample);
		if (null != metas && metas.size() > 0) {
			for (PostMeta meta : metas) {
				model.addAttribute(meta.getMetaKey(), meta.getMetaValue());
			}

		}
		model.addAttribute("post", post);
		String theme = thymeleafVariUtil.getSysVar("site_theme");
	
		return "blog/" + theme + "/" + post.getTemplate();
	}

	public void setNavList(List<Post> lists, Serializable navId) {
		Post navPost = postService.selectByKey(navId);
		lists.add(navPost);
		if (null != navPost.getPostParent() && navPost.getPostParent() > 0) {
			this.setNavList(lists, navPost.getPostParent());
		}
	}

	/***
	 * 
	 * 通过导航跳转到指定的分类文章列表
	 */
	@RequestMapping("/category/{navId}")
	public String categoryNav(@PathVariable(name = "navId") String navId, Model model) {
		Post navPost;
		if (NumberUtils.isDigits(navId)) {
			navPost = postService.selectByKey(navId);
		} else {
			Example example = new Example(Post.class);
			example.createCriteria().andEqualTo("postName", navId);
			List<Post> posts = postService.selectByExample(example);
			navPost = posts.size() == 0 ? null : posts.get(0);
		}
		if (null == navPost) {
			return "404";
		}
		String termId = navPost.getPostContent();
		Term term = termService.selectByKey(termId);
		model.addAttribute("navPost", navPost);
		model.addAttribute("term", term);
		model.addAttribute("navId", navId);
		// 面包屑
		List<Post> breadcrumb = new ArrayList<Post>();
		this.setNavList(breadcrumb, navId);
		Collections.reverse(breadcrumb);
		model.addAttribute("breadcrumb", breadcrumb);
		String theme = joptionService.getOptionValue("site_theme", 0);
		return "blog/" + theme + "/" + term.getTemplate();
	}

	/***
	 * 
	 * 通过导航跳转到指定的分类文章列表
	 */
	@RequestMapping("/tagpost/{tagId}")
	public String tagPostList(@PathVariable(name = "tagId") String tagTermId, Model model) {
		Term term = termService.selectByKey(tagTermId);
		model.addAttribute("term", term);
		model.addAttribute("tagId", tagTermId);
		String theme = thymeleafVariUtil.getSysVar("site_theme");
		return "blog/" + theme + "/" + term.getTemplate();
	}
}
