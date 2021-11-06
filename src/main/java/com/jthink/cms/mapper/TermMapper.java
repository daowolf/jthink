package com.jthink.cms.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jthink.cms.entity.Term;
import com.jthink.common.config.TkMapper;

public interface TermMapper extends TkMapper<Term> {
	public List<Term> queryPostTermsByTaxon(@Param("taxonomy") String taxonomy,
			@Param("objectId") Serializable objectId);

	public List<Term> queryHostPostTermsByTaxon(@Param("taxonomy") String taxonomy,@Param("counts") int counts);

	public int updateCounts(@Param("counts") String counts, @Param("termId") Serializable termId);
}