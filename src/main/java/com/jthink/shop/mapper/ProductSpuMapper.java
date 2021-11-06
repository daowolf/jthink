package com.jthink.shop.mapper;

import java.util.List;

import com.jthink.common.config.TkMapper;
import com.jthink.shop.entity.ProductSpu;

public interface ProductSpuMapper extends TkMapper<ProductSpu> {
	List<ProductSpu> getProductSpu(Integer productId);
}