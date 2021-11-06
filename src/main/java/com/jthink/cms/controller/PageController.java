package com.jthink.cms.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.PostState;
import com.jthink.cms.constants.PostsType;
import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/cms/page")
public class PageController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService postsService;

	@RequestMapping({ "", "/index" })
	public String postsIndex() {
		return "cms/page/page";
	}

	@RequestMapping("getPageById")
	@ResponseBody
	public ResponseBo getPostById(Long pageId) {
		Post post = postsService.selectByKey(pageId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("post", post);
		return ResponseBo.ok(data);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> postList(QueryRequest request, Post post) {
		Example example = new Example(Post.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("postType", PostsType.PAGE);
		if (StringUtils.isNotBlank(post.getPostTitle())) {
			criteria.andLike("postTitle", post.getPostTitle());
		}
		Map<String, Object> pageMap = this.selectByPageNumSize(request, () -> postsService.selectByExample(example));
		return pageMap;

	}

	@RequestMapping("/add")
	@ResponseBody
	@SaCheckPermission(value = {"page:add","superadmin"},mode=SaMode.OR)
	public ResponseBo addPost(Post post, Integer postOrder) {
		post.setPostType(PostsType.PAGE);
		post.setPostStatus(PostState.PUBLISH);
		post.setCreateTime(new Date());
		postsService.save(post);
		return ResponseBo.ok("新增页面成功");
	}

	@RequestMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = {"page:update","superadmin"},mode=SaMode.OR)
	public ResponseBo updatePost(Post post, Integer postOrder) {
		post.setUpdateTime(new Date());
		postsService.updateNotNull(post);
		return ResponseBo.ok("更新页面成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = {"page:delete","superadmin"},mode=SaMode.OR)
	public ResponseBo deletePosts(String pageIds) {
		try {
			List<String> ids = Arrays.asList(pageIds.split(","));
			postsService.batchDelete(ids, "id", Post.class);
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			log.error("删除页面失败", e);
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}
}
