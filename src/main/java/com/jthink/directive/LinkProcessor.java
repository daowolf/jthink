package com.jthink.directive;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Jlink;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.JlinkService;
import com.jthink.cms.service.TermService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class LinkProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "links";// 标签名
	private static final String FIELD_SLUG = "slug";
	public static final int PRECEDENCE = 200;

	public LinkProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String slug = tag.getAttributeValue(FIELD_SLUG);
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		TermService termService = appCtx.getBean(TermService.class);
		JlinkService jlinkService = appCtx.getBean(JlinkService.class);
		Example termExample = new Example(Term.class);
		Criteria criteria = termExample.createCriteria();
		criteria.andEqualTo("taxonomy", Taxonomy.linkType).andEqualTo("slug", slug);
		List<Term> linkTerms = termService.selectByExample(termExample);
		if (null != linkTerms && linkTerms.size() > 0) {
			Term linkTerm = linkTerms.get(0);
			List<Jlink> links = jlinkService.queryLinkByTermId(linkTerm.getId());
			structureHandler.setLocalVariable("links", links);
		}
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
	}

}
