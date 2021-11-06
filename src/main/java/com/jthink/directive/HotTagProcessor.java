package com.jthink.directive;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.TermService;

public class HotTagProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "hotTag";// 标签名
	private static final String FIELD_COUNTS = "couns";
	public static final int PRECEDENCE = 200;

	public HotTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String counts = tag.getAttributeValue(FIELD_COUNTS);
		final IEngineConfiguration configuration = context.getConfiguration();
		if (StringUtils.isBlank(counts)) {
			counts = "10";
		}
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		TermService termService = appCtx.getBean(TermService.class);
		List<Term> tags = termService.queryHostPostTermsByTaxon(Taxonomy.tagType, Integer.parseInt(counts));
		if (null != tags && tags.size() > 0) {
			structureHandler.setLocalVariable("tags", tags);
		}
		//
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
		// 整个元素都删除，内容也不存在
		// structureHandler.removeElement();

	}

}
