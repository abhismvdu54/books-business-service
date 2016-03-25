package com.cd.book.mongo.controller;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.mongo.document.BookReviewDocument;
import com.cd.book.mongo.dto.request.AddBookReviewCommentReq;
import com.cd.book.mongo.dto.request.UpdateBookReviewCommentReq;
import com.cd.book.mongo.exception.BookReviewServiceException;
import com.cd.book.mongo.response.BookReviewResponse;
import com.cd.book.mongo.service.BookReviewService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "book/review", produces = {"application/json", "application/xml"})
public class BookReviewController {

	@Resource
	BookReviewService bookReviewService;
	
	/**
	 * Retrieve the book review based on the book id provided
	 * @param bookId
	 */
	@ResponseBody
	@RequestMapping( method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public BookReviewResponse retrieveBookReview(@RequestParam("bookId") String bookId){
		BookReviewResponse response = new BookReviewResponse();
		try {
			 response = bookReviewService.retrieveBookReview(bookId);
			 
		} catch (BookReviewServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * insert a new book review comment 
	 * @param bookReviewDoc
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public BookReviewResponse insertBookReview(@RequestBody BookReviewDocument bookReviewDocument){
		BookReviewResponse response = new BookReviewResponse();
	
		try {
			 response = bookReviewService.insertBookReview(bookReviewDocument);
			 
		} catch (BookReviewServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	/**
	 * Updates the book review edited by a user
	 * @param bookReviewDoc
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public BookReviewResponse updateBookReview(@RequestBody BookReviewDocument bookReviewDoc){
		BookReviewResponse response = new BookReviewResponse();
		try {
			 response = bookReviewService.updateBookReview(bookReviewDoc);
			 
		} catch (BookReviewServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	//this method is adding the comment in the book review
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
	public BookReviewResponse addBookReviewComment(@RequestBody AddBookReviewCommentReq bookReviewCommentReq){
		BookReviewResponse response = new BookReviewResponse();
		try {
			 response = bookReviewService.addBookReviewComment(bookReviewCommentReq);
			 
		} catch (BookReviewServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
	//this method is  updating the comment in the book review
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.PUT, produces = {"application/json", "application/xml"})
	public BookReviewResponse updateBookReviewComment(@RequestBody UpdateBookReviewCommentReq bookReviewCommentReq){
		BookReviewResponse response = new BookReviewResponse();
		try {
			 response = bookReviewService.updateBookReviewComment(bookReviewCommentReq);
			 
		} catch (BookReviewServiceException e) {
			response.setApplicationCode(e.getApplicationCode());
			response.setCode(e.getCode());
			response.setDeveloperMessage(e.getLocalizedMessage());
		}
		return response;
	}
	
}
