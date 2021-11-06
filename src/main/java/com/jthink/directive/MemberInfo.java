package com.jthink.directive;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.member.entity.Member;
import com.jthink.security.util.StpMemberUtil;
import com.jthink.shop.service.MemberService;

public class MemberInfo extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "memberInfo";// 标签名
	public static final int PRECEDENCE = 200;

	public MemberInfo(String dialectPrefix) {
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
		if(StpMemberUtil.isLogin()) {
			Integer loginId= StpMemberUtil.getLoginIdAsInt();
			MemberService memberService = appCtx.getBean(MemberService.class);
			Member member = memberService.selectByKey(loginId);
			structureHandler.setLocalVariable("member", member);
		}
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
	}

}
