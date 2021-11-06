package com.jthink.shop.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

import com.jthink.common.config.TkMapper;
import com.jthink.shop.entity.ProductSku;

public interface ProductSkuMapper extends TkMapper<ProductSku> {
	Integer updateStock(@Param("skuId") Serializable skuId, @Param("stock") Integer stock);
}