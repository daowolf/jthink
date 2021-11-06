package com.jthink.cms.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.Jlink;
import com.jthink.cms.mapper.JlinkMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JlinkService extends BaseService<Jlink> {
	@Autowired
	JlinkMapper jlinkMapper;
	public List<Jlink> queryLinkByTermId(Serializable termId){
		return jlinkMapper.queryLinkByTermId(termId);
	}

}
