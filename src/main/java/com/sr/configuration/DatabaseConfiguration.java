package com.sr.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

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
		this.initializeDatabase(dataSource);
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
		this.initializeDatabase(dataSource);
		return dataSource;
	}
	
	@Bean
	@Profile("memDb")
	public DataSource inMemoryDb(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2)
			   .addScript("db/schema.sql")
			   .addScript("db/data.sql");
		return builder.build();
	}
	
	
	//TODO: Database Initialization
	
	@Value("classpath:db/schema.sql")
	private Resource schema;
	
	@Value("classpath:db/data.sql")
	private Resource data;
	
	private void initializeDatabase(DataSource dataSource) {
		DatabasePopulator populator = new ResourceDatabasePopulator(schema, data);
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
}
