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
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jthink.cms.entity.PostComment;
import com.jthink.cms.service.PostCommentService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.security.util.StpMemberUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class MyCommentsTag extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "mycommentList";// 标签名
	private static final String PAGE_SIZE = "pageSize";
	private static final String PAGE_NUM = "pageNum";
	public static final int PRECEDENCE = 200;

	public MyCommentsTag(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		final IEngineConfiguration configuration = context.getConfiguration();
		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		PostCommentService commentService = appCtx.getBean(PostCommentService.class);
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
		 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
		 */
		Example example=new Example(PostComment.class);
		Criteria criteria=example.createCriteria();
		Criteria postCri=example.createCriteria();
		if(StpMemberUtil.isLogin()) {
			Integer memberId= StpMemberUtil.getLoginIdAsInt();
			criteria.orEqualTo("userId", memberId);
		}
		example.and(postCri);
	
		PageInfo<?> pageInfo = this.selectByPageNumSize(queryRequest,
				() -> commentService.selectByExample(example));
		structureHandler.setLocalVariable("pageInfo", pageInfo);
	}

	protected PageInfo<?> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return pageInfo;
	}
}
