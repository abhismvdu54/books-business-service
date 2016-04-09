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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.business.domain.request.BookSearchCriteriaRequest;
import com.cd.book.business.domain.response.transformer.BookResponseTransformer;
import com.cd.book.business.service.BookSearchService;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.SearchedBookBSResponse;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
public class BooksSearchController {

	@Resource
	BookSearchService bookSearchService;

	@Resource
	BookResponseTransformer bookResponseTransformer;
	
	/**
	 * This rest api is for searching the books based on the advanced search options
	 * @param bookSearchCriteria
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/search/books/{pageNo}", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public  SearchedBookBSResponse searchBooks(@RequestBody BookSearchCriteriaRequest bookSearchCriteria, 
			@PathVariable ("pageNo") String pageNo){
		
		SearchedBookBSResponse response = null;
		try {
		response = bookSearchService.searchBooks(bookSearchCriteria, pageNo);
		} catch (BookBusinessServiceException e) {
			return (SearchedBookBSResponse) bookResponseTransformer.buildExceptionResponse(e, response);
		}
		
		return (SearchedBookBSResponse) bookResponseTransformer.transformIntoSuccessResponse(response);
	}
	
	/**
	 * This rest api is for searching the books based on wild card. we can search any book based on some search text
	 * @param subject
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/books/search/bySubject/{subject}/{pageNo}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public  SearchedBookBSResponse searchBooksByWildCard(@PathVariable ("subject") String subject,
			@PathVariable ("pageNo") String pageNo){
		SearchedBookBSResponse response = null;
		try {
		response = bookSearchService.searchBooksBySubject(subject, pageNo);
		} catch (BookBusinessServiceException e) {
			return (SearchedBookBSResponse) bookResponseTransformer.buildExceptionResponse(e, response);
		}
		
		return (SearchedBookBSResponse) bookResponseTransformer.transformIntoSuccessResponse(response);
	}
}
