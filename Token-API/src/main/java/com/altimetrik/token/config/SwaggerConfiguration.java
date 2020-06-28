package com.altimetrik.token.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket tokenApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("TOKEN").apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/v1.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("***** TOKEN API SWAGGER DOCUMENTATION *****")
				.description("TOKEN API DETAILS").version("1.0").build();
	}

}
