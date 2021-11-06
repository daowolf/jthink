package com.jthink.shop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.OrderItem;
import com.jthink.shop.mapper.OrderItemMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderItemService extends BaseService<OrderItem> {
	@Autowired
	private OrderItemMapper orderItemMapper;

	public List<OrderItem> getOrderItems(Integer orderId) {
		Example example = new Example(OrderItem.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		example.setOrderByClause("id");
		return this.selectByExample(example);
	}

	public int deleteOrderItems(Integer orderId) {
		Example example = new Example(OrderItem.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		return orderItemMapper.deleteByExample(example);
	}

	public int deleteOrderItems(String ids) {
		List<String> list = Arrays.asList(ids.split(","));
		return this.batchDelete(list, "id", OrderItem.class);
	}

}
