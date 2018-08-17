package com.partha.productService.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.partha.productService.repositories")
public class MongoConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		//UserCredentials credentials = new UserCredentials(env.getProperty("spring.data.mongodb.username"), env.getProperty("spring.data.mongodb.password"));
		//MongoCredential credentials= MongoCredential.createMongoCRCredential("admin", "mydatabase", "password".toCharArray());
		MongoClient mongoClient = new MongoClient(Arrays.asList(
				 //  new ServerAddress("localhost", 27017),
				 //   new ServerAddress("localhost", 27018),
				    new ServerAddress(env.getProperty("spring.data.mongodb.host"), Integer.parseInt(env.getProperty("spring.data.mongodb.port"))))); 
		return new SimpleMongoDbFactory(mongoClient, env.getProperty("spring.data.mongodb.database"));
				
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());				
		return mongoTemplate;
		
	}

}
