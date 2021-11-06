package com.jthink.directive;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.security.util.StpMemberUtil;

public class LoginProcessor extends AbstractAttributeTagProcessor {
	private static final String TAG_NAME = "mlogin";
	private static final int PRECEDENCE = 1000;// 优先级

	public LoginProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, TAG_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		// 按钮所在的html
		String templateName = tag.getTemplateName();
		// <div jk:mlogin="true">121</div> true为attributeValue值
		boolean login = StpMemberUtil.isLogin();
		Boolean attVal = Boolean.parseBoolean(attributeValue);
		if (attVal.booleanValue() == login) {
			return;
		}

		// 如果上非判断都不符合条件，默认删除当前元素，也就是不显示
		structureHandler.removeElement();
	}

}
