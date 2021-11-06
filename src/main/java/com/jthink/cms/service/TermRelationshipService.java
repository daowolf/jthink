package com.jthink.cms.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.mapper.TermRelationshipMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TermRelationshipService extends BaseService<TermRelationship> {
	@Autowired
	private TermRelationshipMapper termRelationshipMapper;

	public List<TermRelationship> getRelationsByTaxon(Serializable objectId, String taxonomy) {
		return termRelationshipMapper.getRelationsByTaxon(objectId, taxonomy);
	}

	public int deleteRelationByTaxon(String taxonomy, Serializable objectId) {
		return termRelationshipMapper.deleteRelationByTaxon(taxonomy, objectId);
	}
}
