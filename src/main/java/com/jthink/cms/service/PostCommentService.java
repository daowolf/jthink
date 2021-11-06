package com.jthink.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.PostComment;
import com.jthink.cms.mapper.PostCommentMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PostCommentService extends BaseService<PostComment>{
	@Autowired
	private PostCommentMapper mapper;

}
