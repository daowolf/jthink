package com.jthink.shop.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.ProductSpuValue;
import com.jthink.shop.mapper.ProductSpuValueMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProductSpuValueService extends BaseService<ProductSpuValue> {
	@Autowired
	private ProductSpuValueMapper productSpuValueMapper;

	public List<ProductSpuValue> getValsBySpu(Serializable spuId) {
		Example example = new Example(ProductSpuValue.class);
		example.createCriteria().andEqualTo("spuId", spuId);
		return productSpuValueMapper.selectByExample(example);
	}

	public List<ProductSpuValue> getSelVals(Serializable productId, Serializable spuId) {
		return productSpuValueMapper.getSelVals(productId, spuId);
	}

}
