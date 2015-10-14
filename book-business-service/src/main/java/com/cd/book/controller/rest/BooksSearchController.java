package com.cd.book.controller.rest;
/**
 * @author Abhishek
 * 
 */
import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.business.service.BookSearchService;
import com.cd.book.dao.UserRepository;
import com.cd.book.response.SearchedBookBSResponse;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
@RequestMapping(value="api/books/search")
public class BooksSearchController {

	@Resource
	private UserRepository userRepository;
	
	@Resource
	BookSearchService bookSearchService;

	@ResponseBody
	@RequestMapping(value = "/bySubject/{subject}/{pageNo}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public  SearchedBookBSResponse searchBooksBySubject(@PathVariable String subject, @PathVariable String pageNo){
		int pageNoInt = Integer.parseInt(pageNo);
		SearchedBookBSResponse response = bookSearchService.searchBySubject(subject, pageNoInt);
		return response;
	}
}
