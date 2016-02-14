package com.cd.book.controller.rest;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.business.domain.request.BookRequest;
import com.cd.book.business.service.BookService;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.BooksInfoResponse;
import com.cd.book.response.transformer.BookBusinessServiceExceptionTransformer;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
public class BookController {

	@Resource
	BookBusinessServiceExceptionTransformer exceptionTransformer;
	
	@Resource
	BookService bookService;
	
	@ResponseBody
	@RequestMapping(value = "/books/info/byOlids", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public BooksInfoResponse  getBookInfoByOlid(@RequestBody BookRequest olids){
		BooksInfoResponse response = null;
		try {
			response = bookService.getBookInfoByOlids(olids);
		} catch (BookBusinessServiceException e) {
			exceptionTransformer.buildServiceExceptionResponse(response, e);
		}
		return response;
	}
}
