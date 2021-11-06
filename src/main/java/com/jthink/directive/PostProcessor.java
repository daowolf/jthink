package com.jthink.directive;

import java.util.List;
import java.util.function.Supplier;

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
import com.jthink.cms.constants.PostsType;
import com.jthink.cms.service.PostService;
import com.jthink.common.entity.QueryRequest;

public class PostProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "postList";// 标签名
	private static final String FIELD_CATEGORY = "categoryId";
	private static final String PAGE_SIZE = "pageSize";
	private static final String PAGE_NUM = "pageNum";
	public static final int PRECEDENCE = 200;

	public PostProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String categoryIdStr = tag.getAttributeValue(FIELD_CATEGORY);
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		PostService postService = appCtx.getBean(PostService.class);
		String pageSize = tag.getAttributeValue(PAGE_SIZE);
		pageSize = pageSize == null ? "10" : pageSize;
		String pageNum = "1";
		HttpServletRequest request = ctx.getRequest();
		if (StringUtils.isNotEmpty(request.getParameter(PAGE_SIZE))) {
			pageSize = request.getParameter(PAGE_SIZE);
		}
		if (StringUtils.isNotEmpty(request.getParameter(PAGE_NUM))) {
			pageNum = request.getParameter(PAGE_NUM);
		}
		if (StringUtils.isNotBlank(categoryIdStr)) {
			/*
			 * Parse the attribute value as a Thymeleaf Standard
			 * Expression,通过表达式可以获取categoryId="${category.id}" 变量中的值，否则直接获取categoryId="23"
			 */
			final IStandardExpression expression = parser.parseExpression(context, categoryIdStr);
			/*
			 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
			 */
			Object categoryId = expression.execute(context);

			QueryRequest queryRequest = new QueryRequest();
			queryRequest.setPageNum(Integer.parseInt(pageNum));
			queryRequest.setPageSize(Integer.parseInt(pageSize));
			PageInfo<?> pageInfo = this.selectByPageNumSize(queryRequest,
					() -> postService.queryPostsByTermId(PostsType.POSTS, categoryId.toString()));
			structureHandler.setLocalVariable("pageInfo", pageInfo);
		} else {
			QueryRequest queryRequest = new QueryRequest();
			queryRequest.setPageNum(Integer.parseInt(pageNum));
			queryRequest.setPageSize(Integer.parseInt(pageSize));
			PageInfo<?> pageInfo = this.selectByPageNumSize(queryRequest,
					() -> postService.queryPostsByTermId(PostsType.POSTS, null));
			structureHandler.setLocalVariable("pageInfo", pageInfo);
		}

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
