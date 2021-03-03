package com.think.ocr.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {
	
	private final OpenApiConfigurationProperties properties;
	
	@Bean
    public OpenAPI openApi() {
		return new OpenAPI()
	            .info(new Info()
	                .title(properties.getTitle())
	                .version(properties.getVersion())
	                .description(properties.getDescription()));
	}
	
}
