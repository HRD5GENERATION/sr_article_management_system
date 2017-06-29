package com.sr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FileUploadConfiguration extends WebMvcConfigurerAdapter{

	@Description("Mapping resource to file system location")
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);		
		registry.addResourceHandler("/resources/images/**")
				.addResourceLocations("file:/opt/images/");
	}
}
