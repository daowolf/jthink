package com.jthink.directive;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jthink.cms.entity.TermRelationship;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermRelationshipService;
import com.jthink.common.entity.QueryRequest;

public class RelatedPostProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "relatedPosts";// 标签名
	private static final String FIELD_POSTID = "postId";
	private static final String FIELD_TAXONOMY = "taxonomy";
	private static final String PAGE_SIZE = "pageSize";
	private static final String PAGE_NUM = "pageNum";
	// 热门文章
	public static final int PRECEDENCE = 200;

	public RelatedPostProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String postIdStr = tag.getAttributeValue(FIELD_POSTID);
		String taxonomy = tag.getAttributeValue(FIELD_TAXONOMY);
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		PostService postService = appCtx.getBean(PostService.class);
		TermRelationshipService relationService = appCtx.getBean(TermRelationshipService.class);
		String pageSize = tag.getAttributeValue(PAGE_SIZE);
		pageSize = pageSize == null ? "10" : pageSize;
		String pageNum = "1";
		HttpServletRequest request = ctx.getRequest();
		if (StringUtils.isNotBlank(request.getParameter(PAGE_SIZE))) {
			pageSize = request.getParameter(PAGE_SIZE);
		}
		if (StringUtils.isNotBlank(request.getParameter(PAGE_NUM))) {
			pageNum = request.getParameter(PAGE_NUM);
		}
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setPageNum(Integer.parseInt(pageNum));
		queryRequest.setPageSize(Integer.parseInt(pageSize));
		/*
		 * Parse the attribute value as a Thymeleaf Standard
		 * Expression,通过表达式可以获取categoryId="${category.id}" 变量中的值，否则直接获取categoryId="23"
		 */
		final IStandardExpression expression = parser.parseExpression(context, postIdStr);
		/*
		 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
		 */
		Object postId = expression.execute(context);
		List<TermRelationship> ships = relationService.getRelationsByTaxon(postId.toString(), taxonomy);
		List<Integer> termIds = ships.stream().map(TermRelationship::getTermId).collect(Collectors.toList());
		PageInfo<?> pageInfo = this.selectByPageNumSize(queryRequest, () -> postService.queryByTermIds(termIds));
		structureHandler.setLocalVariable("pageInfo", pageInfo);

		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
		// 整个元素都删除，内容也不存在
		// structureHandler.removeElement();
	}

	protected PageInfo<?> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return pageInfo;
	}

}
