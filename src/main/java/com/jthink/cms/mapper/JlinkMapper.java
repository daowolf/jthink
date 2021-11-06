package com.jthink.cms.mapper;

import java.io.Serializable;
import java.util.List;

import com.jthink.cms.entity.Jlink;
import com.jthink.common.config.TkMapper;

public interface JlinkMapper extends TkMapper<Jlink> {
	public List<Jlink> queryLinkByTermId(Serializable termId);
}