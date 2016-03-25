package com.cd.book.mongo.cofig.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = { "com.cd.book.mongo.repository" })
public class MongoPersistenceConfig {

	 @Autowired
	 private Environment env;
	 
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception{
		String host = env.getProperty("mongo.host");
		int portNo = Integer.parseInt(env.getProperty("mongo.port"));
		
		return new SimpleMongoDbFactory(new MongoClient(host, portNo), "review");
	}
	
	public @Bean
	MongoTemplate mongoTemplate() throws Exception{
	MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
	System.out.println("test database connected ");
	return mongoTemplate;
	}
}
