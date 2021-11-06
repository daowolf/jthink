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
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.TermService;

public class PostTagProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "postTag";// 标签名
	private static final String FIELD_SLUG = "postId";
	public static final int PRECEDENCE = 200;

	public PostTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String postIdStr = tag.getAttributeValue(FIELD_SLUG);
		final IEngineConfiguration configuration = context.getConfiguration();
		if (StringUtils.isNotBlank(postIdStr)) {
			/*
			 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
			 */

			final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
			/*
			 * Parse the attribute value as a Thymeleaf Standard
			 * Expression,通过表达式可以获取postId="${postId.id}" 变量中的值，否则直接获取postId="23"
			 */
			final IStandardExpression expression = parser.parseExpression(context, postIdStr);
			/*
			 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
			 */
			Object postId = expression.execute(context);

			ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
			WebEngineContext ctx = (WebEngineContext) context;
			TermService termService = appCtx.getBean(TermService.class);
			List<Term> tags = termService.queryPostTermsByTaxon(Taxonomy.tagType, postId.toString());
			if (null != tags && tags.size() > 0) {
				structureHandler.setLocalVariable("tags", tags);
			}
		}

		//
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
		// 整个元素都删除，内容也不存在
		// structureHandler.removeElement();

	}

}
