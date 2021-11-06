package com.jthink.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.common.entity.Joption;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JoptionService extends BaseService<Joption> {
	/**
	 * 按照指定的参数名称的值
	 */
	public String getOptionValue(String optionName,Integer blogId) {
		Example example = new Example(Joption.class);
		example.createCriteria().andEqualTo("optionName", optionName).andEqualTo("blogId", blogId);
		List<Joption> opts = this.selectByExample(example);
		if (null != opts && opts.size() > 0) {
			return opts.get(0).getOptionValue();
		}
		return null;
	}

}
