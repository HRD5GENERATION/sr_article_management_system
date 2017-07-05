package com.sr.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("classpath:ams.properties")
public class FileUploadConfiguration extends WebMvcConfigurerAdapter{

	@Value("${file.server.path}")
	private String SERVER_PATH;
	
	@Value("${file.client.path}")
	private String CLIENT_PATH;
	
	@Description("Mapping resource for uploading to file system location")
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/resources/images/**").addResourceLocations("file:/opt/images/");
		registry.addResourceHandler(CLIENT_PATH + "/**").addResourceLocations("file:" + SERVER_PATH);
	}
}
