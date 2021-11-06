package com.jthink.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jthink.cms.entity.PostMeta;
import com.jthink.common.service.BaseService;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PostMetaService extends BaseService<PostMeta> {

}
