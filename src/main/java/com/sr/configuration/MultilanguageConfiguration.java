package com.sr.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MultilanguageConfiguration extends WebMvcConfigurerAdapter{

	private String []basenames = new String[]{ 
			  "i18n/article/index/messages", 
			  "i18n/article/view/messages", 
			  "i18n/validations/messages"
	};
	
	/*@Value("${message.basenames}")
	private String[] basenames;*/
	
	@Bean
	@Description("Configure default locale")
	public LocaleResolver localeResolver(){
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	} 
	
	@Bean
	@Description("Allow change language by param in url")
	public LocaleChangeInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
	
	@Override
	@Description("Add LocaleChangeIntercepter to registry")
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	@Description("Configuration message source location")
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames(basenames);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
