package com.jthink.cms.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jthink.cms.entity.TermRelationship;
import com.jthink.common.config.TkMapper;

public interface TermRelationshipMapper extends TkMapper<TermRelationship> {
	public List<TermRelationship> getRelationsByTaxon(@Param("objectId") Serializable objectId,@Param("taxonomy") String taxonomy);
	public int  deleteRelationByTaxon(@Param("taxonomy") String taxonomy,@Param("objectId") Serializable objectId);
}