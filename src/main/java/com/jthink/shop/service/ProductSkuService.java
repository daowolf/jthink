package com.jthink.shop.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.ProductSku;
import com.jthink.shop.mapper.ProductSkuMapper;
import com.jthink.shop.mapper.SpuvalRelationMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProductSkuService extends BaseService<ProductSku> {
	@Autowired
	private ProductSkuMapper productSkuMapper;
	@Autowired
	private SpuvalRelationMapper spuvalRelationMapper;

	public List<ProductSku> getSkus(Integer productId) {
		Example example = new Example(ProductSku.class);
		example.createCriteria().andEqualTo("productId", productId);
		return this.selectByExample(example);
	}

	public ProductSku getSku(Serializable productId, String spuvalIds) {
		Example example = new Example(ProductSku.class);
		example.createCriteria().andEqualTo("productId", productId).andEqualTo("spuvalIds", spuvalIds);
		return productSkuMapper.selectOneByExample(example);
	}
	public Integer updateStock(Serializable skuId, Integer stock) {
		return productSkuMapper.updateStock(skuId, stock);
	}

}
