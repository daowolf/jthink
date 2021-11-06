package com.jthink.cms.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.Post;
import com.jthink.cms.mapper.PostMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PostService extends BaseService<Post> {
	@Autowired
	private PostMapper postsMapper;

	public List<Post> queryPostsByTermId(String postType, Serializable termId) {
		return postsMapper.queryPostsByTermId(postType, termId);
	}

	public List<Post> queryTopPostsByTermId(String postType, Serializable termId) {
		return postsMapper.queryTopPostsByTermId(postType, termId);
	}

	public List<Post> queryHotPosts(Serializable termId) {
		return postsMapper.queryHotPosts(termId);
	}

	public Post selectPostWithChild(Serializable postId) {
		return postsMapper.selectPostWithChild(postId);
	}

	public Integer updateAddSaleCount(Integer addCount, Serializable postId) {
		return postsMapper.updateAddSaleCount(addCount, postId);
	}

	public Integer updateAddCommentCount(Integer addCount, Serializable postId) {
		return postsMapper.updateAddCommentCount(addCount, postId);
	}

	public List<Post> queryByTermIds(List<Integer> termIds) {
		return postsMapper.queryByTermIds(termIds);
	}
	public List<Post> queryFavoritePosts(Serializable memberId){
		return postsMapper.queryFavoritePosts(memberId);
	}

}
