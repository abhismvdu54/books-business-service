package com.cd.book.controller.rest;
/**
 * @author Mayank
 * 
 */
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.entity.User;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
public class AppConntrollerRest {
	 @RequestMapping(value = "/home", method = RequestMethod.GET, headers = "Accept=application/json")
	 public @ResponseBody User create() {
		 User user1=new User();
		 //user1.setEmail("MayankKumar0078@gmail.com");
		 //user1.setPassword("mayank");
		 return user1;
	 }
}
