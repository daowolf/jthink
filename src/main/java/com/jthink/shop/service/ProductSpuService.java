package com.jthink.shop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.ProductSpu;
import com.jthink.shop.entity.SpuRelation;
import com.jthink.shop.mapper.ProductSpuMapper;
import com.jthink.shop.mapper.SpuRelationMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProductSpuService extends BaseService<ProductSpu> {
	@Autowired
	private ProductSpuMapper productSpuMapper;
	@Autowired
	private SpuRelationMapper spuRelationMapper;

	/**
	 * 根据商品ID查询已经关联的规格
	 */
	public List<ProductSpu> getProductSpu(Integer productId) {
		return productSpuMapper.getProductSpu(productId);
	}

	public int batchDeleteSpuRelation(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		Example example = new Example(SpuRelation.class);
		example.createCriteria().andIn("id", ids);
		return spuRelationMapper.deleteByExample(example);
	}

	public void saveSpuRelation(Integer productId, String ids_str) {
		String[] ids = ids_str.split(",");
		for (int i = 0; i < ids.length; i++) {
			Integer spuId = Integer.parseInt(ids[i]);
			Example example = new Example(SpuRelation.class);
			example.createCriteria().andEqualTo("productId", productId).andEqualTo("spuId", spuId);
			int count = spuRelationMapper.selectCountByExample(example);
			if (count == 0) {
				SpuRelation relation = new SpuRelation();
				relation.setProductId(productId);
				relation.setSpuId(Integer.parseInt(ids[i]));
				spuRelationMapper.insert(relation);
			}
		}
	}

}
