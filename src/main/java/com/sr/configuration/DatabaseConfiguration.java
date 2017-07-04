package com.sr.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	@Profile("devDb")
	public DataSource devDb(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dbarticle");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123");
		return dataSource;
	}
	
	@Bean
	@Profile("proDb")
	public DataSource proDb(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://128.199.182.219:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123456");
		return dataSource;
	}
}
