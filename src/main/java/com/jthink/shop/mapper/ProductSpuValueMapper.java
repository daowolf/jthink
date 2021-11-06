package com.jthink.shop.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jthink.common.config.TkMapper;
import com.jthink.shop.entity.ProductSpuValue;

public interface ProductSpuValueMapper extends TkMapper<ProductSpuValue> {
	List<ProductSpuValue> getSelVals(@Param("productId") Serializable productId, @Param("spuId") Serializable spuId);
}