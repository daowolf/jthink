package com.jthink.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.OrderDelivery;
import com.jthink.shop.mapper.OrderDeliveryMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderDeliveryService extends BaseService<OrderDelivery> {
	@Autowired
	private OrderDeliveryMapper deliveryMapper;

	public List<OrderDelivery> getDeliveryByOrderId(Integer orderId) {
		Example example = new Example(OrderDelivery.class);
		example.createCriteria().andEqualTo("orderId", orderId);
		return this.selectByExample(example);
	}
}
