package com.jthink.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.service.BaseService;
import com.jthink.shop.entity.Coupon;
import com.jthink.shop.mapper.CouponMapper;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CouponService extends BaseService<Coupon> {
	@Autowired
	private CouponMapper couponMapper;

}
