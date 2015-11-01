package com.cd.book.config.bookShelf;

import javax.validation.Validator;
import org.springframework.validation.beanvalidation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookShelfConfiguration {

	@Bean
	public Validator validator(){
		Validator validator = new LocalValidatorFactoryBean();
		return validator;
	}
}
