package com.jthink.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.MemberOrder;
import com.jthink.shop.mapper.MemberOrderMapper;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MemberOrderService extends BaseService<MemberOrder> {
	@Autowired
	private MemberOrderMapper memberOrderMapper;
	public int updateRemarks(String remarks, String orderId) {
		return memberOrderMapper.updateRemarks(remarks, orderId);
	}

}
