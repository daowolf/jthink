package com.jthink.directive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectiveConfig {
	@Bean
	public JthinkDialect getJthinkDialect() {
		return new JthinkDialect();
	}

}
