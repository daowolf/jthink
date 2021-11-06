package com.jthink.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jthink.cms.entity.JthinkImage;
import com.jthink.common.config.TkMapper;

public interface JthinkImageMapper extends TkMapper<JthinkImage> {
	public List<JthinkImage> queryPostImages(@Param("postId") Integer postId);

}
