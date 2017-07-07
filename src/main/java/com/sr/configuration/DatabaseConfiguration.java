package com.sr.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:ams.properties")
public class DatabaseConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	@Profile("devDb")
	public DataSource devDb(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("dev.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("dev.datasource.url"));
		dataSource.setUsername(env.getProperty("dev.datasource.username"));
		dataSource.setPassword(env.getProperty("dev.datasource.password"));
		return dataSource;
	}
	
	@Bean
	@Profile("proDb")
	public DataSource proDb(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("pro.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("pro.datasource.url"));
		dataSource.setUsername(env.getProperty("pro.datasource.username"));
		dataSource.setPassword(env.getProperty("pro.datasource.password"));
		return dataSource;
	}
}
