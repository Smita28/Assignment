package com.org.recipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is for Swagger.
 * 
 * @author smita
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {



	/**
	 * This method is for swagger config by Docket
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.org.recipe")).build();
	}

	/**
	 * This method is for web configuration.
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			/**
			 * This method is for swagger config
			 * 
			 * @return
			 */
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
				registry.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/");
			}
		};
	}


}
