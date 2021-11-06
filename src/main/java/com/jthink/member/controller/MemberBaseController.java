package com.jthink.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jthink.common.entity.QueryRequest;

public abstract class MemberBaseController {
	private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<>();
		rspData.put("rows", pageInfo.getList());
		rspData.put("total", pageInfo.getTotal());
		return rspData;
	}

	protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return getDataTable(pageInfo);
	}
}
