package com.jthink.cms.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jthink.cms.entity.Post;
import com.jthink.common.config.TkMapper;

public interface PostMapper extends TkMapper<Post> {
	public List<Post> queryPostsByTermId(@Param(value = "postType") String postType,
			@Param(value = "termId") Serializable termId);

	public List<Post> queryTopPostsByTermId(@Param(value = "postType") String postType,
			@Param(value = "termId") Serializable termId);

	public Integer updateAddSaleCount(@Param(value = "addCount") Integer addCount,
			@Param(value = "postId") Serializable postId);

	public Integer updateAddCommentCount(@Param(value = "addCount") Integer addCount,
			@Param(value = "postId") Serializable postId);

	public List<Post> queryHotPosts(Serializable termId);

	public Post selectPostWithChild(Serializable postId);

	public List<Post> queryByTermIds(List<Integer> termIds);

	public List<Post> queryFavoritePosts(Serializable memberId);

}