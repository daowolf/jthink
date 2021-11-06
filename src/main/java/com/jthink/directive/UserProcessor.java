package com.jthink.directive;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.permission.entity.JthinkUser;
import com.jthink.permission.service.UserService;

import cn.dev33.satoken.stp.StpUtil;

public class UserProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "currentUser";// 标签名
	public static final int PRECEDENCE = 200;

	public UserProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		UserService userService = appCtx.getBean(UserService.class);
		Integer userId = StpUtil.getLoginIdAsInt();
		JthinkUser user = userService.selectByKey(userId);
		structureHandler.setLocalVariable("user", user);
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
	}

}
