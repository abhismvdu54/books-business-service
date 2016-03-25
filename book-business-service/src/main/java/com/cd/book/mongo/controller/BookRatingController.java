package com.cd.book.mongo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.mongo.exception.BookRatingServiceException;
import com.cd.book.mongo.response.BookAvgRatingResponse;
import com.cd.book.mongo.response.BookRatingResponse;
import com.cd.book.mongo.service.BookRatingService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "book/rating", produces = {"application/json", "application/xml"})
public class BookRatingController {

	@Resource
	private BookRatingService bookRatingService;
	/**
	 * Retrieve the book rating based on the book id provided
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public BookRatingResponse getBookRating(@RequestParam("bookId") String bookId){
		BookRatingResponse response = new BookRatingResponse();
		try {
			 response = bookRatingService.retrieveBookRating(bookId);
			 
		} catch (BookRatingServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	/**
	 * Retrieve the book ratings for a list of book ids.
	 * @param List
	 */
	@ResponseBody
	@RequestMapping(value = "/list" ,method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public BookAvgRatingResponse getBookRatings(@RequestBody List<String> bookIds){
		BookAvgRatingResponse response = new BookAvgRatingResponse();
		List<BookAvgRating> listOfAvgBookRating = null;
		try {
			listOfAvgBookRating = bookRatingService.retrieveBookRatings(bookIds);
			//convert into response
			response.setBookAvgRating(listOfAvgBookRating);
			 
		} catch (BookRatingServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Insert the book rating
	 * @param bookRatingDocument
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public BookRatingResponse updateBookRating(@RequestBody BookRatingDocument bookRatingDoc){
		BookRatingResponse response = new BookRatingResponse();
		try {
			 response = bookRatingService.updateBookRating(bookRatingDoc);
			 
		} catch (BookRatingServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public BookRatingResponse insertBookRatings(@RequestBody List<BookRatingDocument> bookRatingDocs){
		BookRatingResponse response = new BookRatingResponse();
		try {
			 response = bookRatingService.insertBookRatings(bookRatingDocs);
			 
		} catch (BookRatingServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
}
