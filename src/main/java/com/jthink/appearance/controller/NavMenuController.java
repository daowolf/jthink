package com.jthink.appearance.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.constants.PostState;
import com.jthink.cms.constants.PostsType;
import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.JthinkTree;
import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.Term;
import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.cms.service.TermService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.permission.utils.TreeUtils;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/appearance/navmenu")
public class NavMenuController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService postsService;
	@Autowired
	private TermRelationshipService termRelationshipService;
	@Autowired
	private TermService termService;

	@RequestMapping({ "", "/index" })
	public String postsIndex(String termId, Model model) {
		Term term = termService.selectByKey(termId);
		model.addAttribute("term", term);
		return "cms/navmenu/navmenu";
	}

	@RequestMapping("getPostById")
	@ResponseBody
	public ResponseBo getPostById(Long postId) {
		Post post = postsService.selectByKey(postId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("post", post);
		return ResponseBo.ok(data);
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Post> postList(QueryRequest request, Post post, Integer termId) {

		List<Post> menus = postsService.queryPostsByTermId(PostsType.MENUITEM, termId);
		return menus;
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addPost(Post post, Integer termId, String type) {
		post.setPostType(PostsType.MENUITEM);
		post.setPostStatus(PostState.PUBLISH);
		if (null == termId || null == type) {
			return ResponseBo.error("导航类型不能为空");
		}
		post.setPostMimeType(type);
		post.setCreateTime(new Date());
		postsService.save(post);
		TermRelationship termRelationship = new TermRelationship();
		termRelationship.setObjectId(post.getId());
		termRelationship.setTermId(termId);
		termRelationshipService.save(termRelationship);
		return ResponseBo.ok("新增导航成功");
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updatePost(Post post, Integer termId, String type) {
		post.setPostType(PostsType.MENUITEM);
		post.setPostStatus(PostState.PUBLISH);
		post.setUpdateTime(new Date());
		post.setPostMimeType(type);
		postsService.updateNotNull(post);
		Example example = new Example(TermRelationship.class);
		example.createCriteria().andEqualTo("objectId", post.getId());
		List<TermRelationship> relations = termRelationshipService.selectByExample(example);
		// 删除文档关联分类
		for (TermRelationship relation : relations) {
			Term term = termService.selectByKey(relation.getTermId());
			if (null != term && term.getTaxonomy().equalsIgnoreCase(Taxonomy.navMenuType)) {
				termRelationshipService.delete(relation.getId());
			}
		}
		TermRelationship termRelationship = new TermRelationship();
		termRelationship.setObjectId(post.getId());
		termRelationship.setTermId(termId);
		termRelationshipService.save(termRelationship);

		return ResponseBo.ok("更新导航成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deletePosts(String postIds) {
		try {
			List<String> ids = Arrays.asList(postIds.split(","));
			postsService.batchDelete(ids, "id", Post.class);
			// 删除关联关系
			for (String postId : ids) {
				Example example = new Example(TermRelationship.class);
				example.createCriteria().andEqualTo("objectId", postId);
				List<TermRelationship> relations = termRelationshipService.selectByExample(example);
				// 删除文档关联分类
				for (TermRelationship relation : relations) {
					Term term = termService.selectByKey(relation.getTermId());
					if (null != term && term.getTaxonomy().equalsIgnoreCase(Taxonomy.navMenuType)) {
						termRelationshipService.delete(relation.getId());
					}
				}
			}
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			log.error("删除文章失败", e);
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/treeList")
	@ResponseBody
	public ResponseBo getTermTreeList(Integer termId) {

		List<JthinkTree<Post>> tree = new ArrayList<>();
		List<Post> terms = postsService.queryPostsByTermId(PostsType.MENUITEM, termId);
		if (null != terms) {
			buildTrees(tree, terms);
			JthinkTree<Post> termTree = TreeUtils.buildTree(tree);
			List<JthinkTree<Post>> treeSource = new ArrayList<JthinkTree<Post>>();
			treeSource.add(termTree);
			return ResponseBo.ok(treeSource);
		}
		return ResponseBo.ok();
	}

	private void buildTrees(List<JthinkTree<Post>> tree, List<Post> posts) {
		posts.forEach(post -> {
			JthinkTree<Post> treeNode = new JthinkTree<Post>();
			treeNode.setTitle(post.getPostTitle());
			treeNode.setId(post.getId().toString());
			if (null != post.getPostParent()) {
				treeNode.setParentId(post.getPostParent().toString());
			}
			tree.add(treeNode);
		});
	}
}
