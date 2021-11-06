package com.jthink.cms.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.PostComment;
import com.jthink.cms.entity.PostMeta;
import com.jthink.cms.entity.Term;
import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.service.PostCommentService;
import com.jthink.cms.service.PostMetaService;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.cms.service.TermService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.utils.ResponseBo;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Controller
@RequestMapping("/system/cms/post")
public class PostController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService postsService;
	@Autowired
	private TermRelationshipService termRelationshipService;
	@Autowired
	private TermService termService;
	@Autowired
	private PostMetaService postMetaService;
	@Autowired
	private PostCommentService postCommentService;

	@RequestMapping({ "", "/index" })
	public String postsIndex() {
		return "cms/post/post";
	}

	@RequestMapping({ "", "/selpost" })
	public String selPostIndex(Model ui, String postType, String termId) {
		Term term = termService.selectByKey(termId);
		ui.addAttribute("termId", termId);
		ui.addAttribute("term", term);
		ui.addAttribute("postType", postType);
		return "cms/sel_post/post";
	}

	@RequestMapping({ "", "/sellist" })
	@ResponseBody
	public Map<String, Object> getSelPosts(QueryRequest request, String postType, String termId) {
		Map<String, Object> postMap = this.selectByPageNumSize(request,
				() -> postsService.queryPostsByTermId(postType, termId));
		return postMap;
	}

	@RequestMapping({ "", "/saveRelation" })
	@ResponseBody
	public ResponseBo saveRelation(String postIds, Integer termId) {
		List<String> ids = Arrays.asList(postIds.split(","));
		for (String id : ids) {
			Example example = new Example(TermRelationship.class);
			example.createCriteria().andEqualTo("objectId", id).andEqualTo("termId", termId);
			List<TermRelationship> ships = termRelationshipService.selectByExample(example);
			if (ships.size() > 0) {
				continue;
			}
			TermRelationship termRelationship = new TermRelationship();
			termRelationship.setObjectId(Integer.parseInt(id));
			termRelationship.setTermId(termId);
			termRelationship.setTermOrder(0);
			termRelationshipService.save(termRelationship);
			termService.updateCounts("+1", termId);
		}
		return ResponseBo.ok();
	}

	@RequestMapping({ "", "/delRelation" })
	@ResponseBody
	public ResponseBo delRelation(String postIds, Integer termId) {
		List<String> ids = Arrays.asList(postIds.split(","));
		for (String id : ids) {
			Example example = new Example(TermRelationship.class);
			example.createCriteria().andEqualTo("objectId", id).andEqualTo("termId", termId);
			termRelationshipService.deleteByExample(example);
			termService.updateCounts("-1", termId);
		}
		return ResponseBo.ok();
	}

	@RequestMapping("getPostById")
	@ResponseBody
	public ResponseBo getPostById(Integer postId) {
		Post post = postsService.selectByKey(postId);
		List<Term> categoryList = termService.queryPostTermsByTaxon(Taxonomy.categoryType, postId);
		List<Integer> taxids = categoryList.stream().map(Term::getId).collect(Collectors.toList());
		List<Term> tagList = termService.queryPostTermsByTaxon(Taxonomy.tagType, postId);
		Example metaExample = new Example(PostMeta.class);
		metaExample.createCriteria().andEqualTo("postId", postId);
		List<PostMeta> metas = postMetaService.selectByExample(metaExample);

		String tags = tagList.stream().map(Term::getName).collect(Collectors.joining(","));
		Map<String, Object> data = new HashMap<String, Object>();
		if (null != metas && metas.size() > 0) {
			Map<String, String> mapMetas = metas.stream()
					.collect(Collectors.toMap(PostMeta::getMetaKey, PostMeta::getMetaValue));
			data.put("metas", mapMetas);
		}
		data.put("post", post);
		data.put("taxids", taxids);
		data.put("tags", tags);
		return ResponseBo.ok(data);
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> postList(QueryRequest request, Post post) {
		Example example = new Example(Post.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("postType", PostsType.POSTS);
		if (StringUtils.isNotBlank(post.getPostTitle())) {
			criteria.andLike("postTitle", post.getPostTitle());
		}
		Map<String, Object> postMap = this.selectByPageNumSize(request, () -> postsService.selectByExample(example));
		return postMap;

	}

	@RequestMapping("/add")
	@ResponseBody
	@SaCheckPermission(value = { "post:add", "superadmin" }, mode = SaMode.OR)
	public ResponseBo addPost(Post post, String termIds, Integer postOrder, String tags) {
		post.setPostType(PostsType.POSTS);
		post.setPostStatus(PostState.PUBLISH);
		post.setCreateTime(new Date());
		post.setStocks(0);
		post.setSaleCounts(0);
		post.setViewCounts(0);
		post.setCommentCount(0);
		postsService.save(post);
		String[] ids = termIds.split(",");
		for (String id : ids) {
			TermRelationship termRelationship = new TermRelationship();
			termRelationship.setObjectId(post.getId());
			termRelationship.setTermId(Integer.parseInt(id));
			termRelationship.setTermOrder(postOrder);
			termRelationshipService.save(termRelationship);
			termService.updateCounts("+1", id);
		}
		// tags
		String[] tagNames = tags.split(",");
		for (String tagName : tagNames) {
			Integer tagId;
			Example example = new Example(Term.class);
			example.createCriteria().andEqualTo("name", tagName).andEqualTo("taxonomy", Taxonomy.tagType);
			List<Term> listTags = termService.selectByExample(example);
			if (null != listTags && listTags.size() > 0) {
				tagId = listTags.get(0).getId();
			} else {
				Term newTag = new Term();
				newTag.setTaxonomy(Taxonomy.tagType);
				newTag.setCreateTime(new Date());
				newTag.setName(tagName);
				newTag.setDescription(tagName);
				newTag.setCounts(0L);
				newTag.setTemplate("postList");
				termService.save(newTag);
				tagId = newTag.getId();
			}
			TermRelationship termRelationship = new TermRelationship();
			termRelationship.setObjectId(post.getId());
			termRelationship.setTermId(tagId);
			termRelationshipService.save(termRelationship);
			// 更新分类文章数量
			termService.updateCounts("+1", tagId);
		}
		return ResponseBo.ok("新增文章成功");
	}

	@RequestMapping("/update")
	@ResponseBody
	@SaCheckPermission(value = { "post:update", "superadmin" }, mode = SaMode.OR)
	public ResponseBo updatePost(Post post, String termIds, Integer postOrder, String tags) {
		post.setUpdateTime(new Date());
		postsService.updateNotNull(post);
		// 删除原来文档关联分类
		termRelationshipService.deleteRelationByTaxon(Taxonomy.categoryType, post.getId());

		// 删除原来文档关tag标签
		termRelationshipService.deleteRelationByTaxon(Taxonomy.tagType, post.getId());

		if (StringUtils.isNotBlank(termIds)) {
			String[] ids = termIds.split(",");
			for (String id : ids) {
				TermRelationship termRelationship = new TermRelationship();
				termRelationship.setObjectId(post.getId());
				termRelationship.setTermId(Integer.parseInt(id));
				termRelationship.setTermOrder(postOrder);
				termRelationshipService.save(termRelationship);
			}
		}
		String[] tagNames = tags.split(",");
		for (String tagName : tagNames) {
			Integer tagId;
			Example example = new Example(Term.class);
			example.createCriteria().andEqualTo("name", tagName).andEqualTo("taxonomy", Taxonomy.tagType);
			List<Term> listTags = termService.selectByExample(example);
			if (null != listTags && listTags.size() > 0) {
				tagId = listTags.get(0).getId();
			} else {
				Term newTag = new Term();
				newTag.setTaxonomy(Taxonomy.tagType);
				newTag.setCreateTime(new Date());
				newTag.setName(tagName);
				newTag.setDescription(tagName);
				newTag.setCounts(0L);
				newTag.setTemplate("postList");
				termService.save(newTag);
				tagId = newTag.getId();
			}
			// 新标签需求增加counts
			termService.updateCounts("+1", tagId);
			TermRelationship termRelationship = new TermRelationship();
			termRelationship.setObjectId(post.getId());
			termRelationship.setTermId(tagId);
			termRelationshipService.save(termRelationship);
		}
		return ResponseBo.ok("更新文章成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	@SaCheckPermission(value = { "post:delete", "superadmin" }, mode = SaMode.OR)
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
					if (null != term && term.getTaxonomy().equalsIgnoreCase(Taxonomy.categoryType)) {
						termRelationshipService.delete(relation.getId());
						termService.updateCounts("-1", relation.getTermId());
					}
				}
			}
			// 删除相关评论
			Example cmtExample = new Example(PostComment.class);
			cmtExample.createCriteria().andIn("commentPostId", ids);
			postCommentService.deleteByExample(cmtExample);
			return ResponseBo.ok("删除成功！");
		} catch (Exception e) {
			log.error("删除文章失败", e);
			return ResponseBo.error("删除失败，请联系网站管理员！");
		}
	}
}
