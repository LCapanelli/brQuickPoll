package com.brquickpoll;

import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig {
	
	//point the right path in the index.html inside the resources/static/swagger-ui
	//use through http://localhost:8080/index.html 
	
	@Inject
	private SpringSwaggerConfig springSwaggerConfig;
	
	@Bean
	public SwaggerSpringMvcPlugin configureSwagger() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new
				
			SwaggerSpringMvcPlugin(this.springSwaggerConfig);
			ApiInfo apiInfo = new ApiInfoBuilder()
						.title("BrQuickPoll REST API WITH SPRING BOOT")
						.description("BrQuickPoll Api for creating and managing polls")
						.termsOfServiceUrl("http://example.com/terms-of-service")
						.contact("info@example.com")
						.license("MIT License")
						.licenseUrl("http://opensource.org/licenses/MIT")
						.build();
	
			swaggerSpringMvcPlugin
						.apiInfo(apiInfo)
						.apiVersion("1.0")
						.includePatterns("/polls/*.*", "/votes/*.*", "/computeresult/*.*");
		return swaggerSpringMvcPlugin;	
	}
	
}
