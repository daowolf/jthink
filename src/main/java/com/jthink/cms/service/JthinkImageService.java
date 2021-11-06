package com.jthink.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.JthinkImage;
import com.jthink.cms.mapper.JthinkImageMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JthinkImageService extends BaseService<JthinkImage> {
	@Autowired
	private JthinkImageMapper jthinkImageMapper;

	public List<JthinkImage> queryPostImages(Integer postId) {
		return jthinkImageMapper.queryPostImages(postId);
	}

}
