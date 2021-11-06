package com.jthink.cms.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.Term;
import com.jthink.cms.mapper.TermMapper;
import com.jthink.common.service.BaseService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TermService extends BaseService<Term> {
	@Autowired
	private TermMapper termMapper;

	public List<Term> queryPostTermsByTaxon(String taxonomy, Serializable postId) {
		return termMapper.queryPostTermsByTaxon(taxonomy, postId);
	}
	public List<Term> queryHostPostTermsByTaxon(String taxonomy,int counts){
		return termMapper.queryHostPostTermsByTaxon(taxonomy,counts);
	}
	public int updateCounts(String counts,Serializable termId) {
		return termMapper.updateCounts(counts,termId);
	}
}
