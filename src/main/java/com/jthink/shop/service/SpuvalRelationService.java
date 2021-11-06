package com.jthink.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.SpuvalRelation;
import com.jthink.shop.mapper.SpuvalRelationMapper;

import tk.mybatis.mapper.entity.Example;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SpuvalRelationService extends BaseService<SpuvalRelation> {
	@Autowired
	private SpuvalRelationMapper spuvalRelationMapper;
	public int selectCountByExample(Example example) {
		int counts=spuvalRelationMapper.selectCountByExample(example);
		return counts;
	}

}
