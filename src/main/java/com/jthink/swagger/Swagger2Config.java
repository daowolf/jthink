package com.jthink.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * Description:
 * </p>
 *
 * @version v1.0.0
 * @create 2020-01-09
 */
@Configuration
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.jthink")).paths(PathSelectors.any()).build()
				.globalRequestParameters(getGlobalRequestParameters());
	}

	// 生成全局通用参数
	private List<RequestParameter> getGlobalRequestParameters() {
		List<RequestParameter> parameters = new ArrayList<>();
		parameters.add(new RequestParameterBuilder().name("Authorization").description("认证token").required(true)
				.in(ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false)
				.build());
		parameters.add(new RequestParameterBuilder().name("version").description("客户端的版本号").required(true)
				.in(ParameterType.QUERY).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false)
				.build());
		return parameters;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("自由领主的Jthink").description("wtsoftware@163.com")
				.contact(new Contact("undead", null, null)).version("V1.0").build();
	}
}