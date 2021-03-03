package com.think.ocr.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "openapi")
public class OpenApiConfigurationProperties {
	
	private String title;
    private String description;
    private String version;

}
