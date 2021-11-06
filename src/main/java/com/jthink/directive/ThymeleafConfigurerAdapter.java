package com.jthink.directive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ThymeleafConfigurerAdapter implements WebMvcConfigurer {
	@Autowired
	private ThymeleafVariUtil thymeleafVariUtil;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		thymeleafVariUtil.configViewVar();
		WebMvcConfigurer.super.configureViewResolvers(registry);
	}

}
