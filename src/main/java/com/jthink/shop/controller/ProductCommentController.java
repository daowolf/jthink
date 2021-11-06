package com.jthink.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.PostComment;
import com.jthink.cms.service.PostCommentService;
import com.jthink.cms.service.PostService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

/***
 * 
 * 文章和商品评论
 */
@Controller
@RequestMapping("/system/shop/comments")
public class ProductCommentController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private PostCommentService postCommentService;

	@RequestMapping({ "", "/index" })
	public String index(String postId, Model model) {
		Post post = postService.selectByKey(postId);
		model.addAttribute("post", post);
		return "shop/comment/comment";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> commentList(QueryRequest request, String postId) {
		Example example = new Example(PostComment.class);
		example.createCriteria().andEqualTo("commentPostId", postId);
		Map<String, Object> commentMap = this.selectByPageNumSize(request,
				() -> postCommentService.selectByExample(example));
		return commentMap;
	}

	@RequestMapping("/getCommentById")
	@ResponseBody
	public ResponseBo getCommentById(Integer commentId) {
		PostComment comment = postCommentService.selectByKey(commentId);
		return ResponseBo.ok(comment);
	}

	/**
	 * 新增评论
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addComment(PostComment comment) {
		comment.setCreateTime(new Date());
		postCommentService.save(comment);
		return ResponseBo.ok(comment.getId());
	}

	/**
	 * 更新评论
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updateComment(PostComment comment) {
		comment.setUpdateTime(new Date());
		postCommentService.updateNotNull(comment);
		return ResponseBo.ok(comment.getId());
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteCategory(String commentIds) {
		List<String> ids = Arrays.asList(commentIds.split(","));
		postCommentService.batchDelete(ids, "id", PostComment.class);
		return ResponseBo.ok();
	}
}
