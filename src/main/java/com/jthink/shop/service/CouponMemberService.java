package com.jthink.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.CouponMember;
import com.jthink.shop.mapper.CouponMemberMapper;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CouponMemberService extends BaseService<CouponMember> {
	@Autowired
	private CouponMemberMapper couponMemberMapper;
	

}
