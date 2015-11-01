package com.cd.book.controller.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cd.book.business.domain.request.bookShelf.BookShelfCreateRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfGetRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfUpdateRequest;
import com.cd.book.business.service.BookShelfService;
import com.cd.book.entity.BookShelf;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.BookShelfResponse;
import com.cd.book.response.transformer.BookShelfResponseTransformer;

@RestController
@EnableAutoConfiguration
@ComponentScan
@Transactional
@RequestMapping(value="api")
public class BookShelfController {

	@Resource
	private BookShelfService bookShelfService;

	@Resource
	private BookShelfResponseTransformer bookShelfResponseTransformer;

	/**
	 * Add new book shelf
	 * @param bookShelfRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bookShelf", method = RequestMethod.POST,
	produces = {"application/json", "application/xml"})
	public BookShelfResponse createBookShelf(@RequestBody BookShelfCreateRequest bookShelfRequest){
		BookShelfResponse bookShelfResponse = new BookShelfResponse();
		BookShelf bookShelf = null;
		try {
			//validate the request
			bookShelfResponseTransformer.validateCreateRequest(bookShelfRequest);

			bookShelf = bookShelfService.addBookShelf(bookShelfRequest);

			bookShelfResponse = bookShelfResponseTransformer.transformIntoResponse(bookShelf);
			bookShelfResponse.setMessage("Book Shelf added successfully");
		} catch (BookBusinessServiceException e) {
			bookShelfResponse = bookShelfResponseTransformer.buildExceptionMessage(e);
		}
		return bookShelfResponse;
	}

	/**
	 * update the book shelf name
	 * @param bookShelfRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bookShelf", method = RequestMethod.PUT,
	produces = {"application/json", "application/xml"})
	public BookShelfResponse updateBookShelf(@RequestBody BookShelfUpdateRequest bookShelfRequest){
		BookShelfResponse bookShelfResponse = new BookShelfResponse();
		BookShelf bookShelf = null;
		try {
			//validate request
			bookShelfResponseTransformer.validateUpdateRequest(bookShelfRequest);

			bookShelf = bookShelfService.updateBookShelf(bookShelfRequest);

			bookShelfResponse = bookShelfResponseTransformer.transformIntoResponse(bookShelf);
			bookShelfResponse.setMessage("Book Shelf updated successfully");
		} catch (BookBusinessServiceException e) {
			bookShelfResponse = bookShelfResponseTransformer.buildExceptionMessage(e);
		}
		return bookShelfResponse;
	}
	/**
	 * soft delete the book shelf
	 * @param bookShelfRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bookShelf", method = RequestMethod.DELETE,
	produces = {"application/json", "application/xml"})
	public BookShelfResponse deleteBookShelf(@RequestBody BookShelfUpdateRequest bookShelfRequest){
		BookShelfResponse bookShelfResponse = new BookShelfResponse();
		BookShelf bookShelf = null;
		try {
			//validate request
			bookShelfResponseTransformer.validateUpdateRequest(bookShelfRequest);

			bookShelf = bookShelfService.deleteBookShelf(bookShelfRequest);

			bookShelfResponse = bookShelfResponseTransformer.transformIntoResponse(bookShelf);
			bookShelfResponse.setMessage("Book Shelf deleted successfully");
		} catch (BookBusinessServiceException e) {
			bookShelfResponse = bookShelfResponseTransformer.buildExceptionMessage(e);
		}
		return bookShelfResponse;
	}

	/** Fetch all the book shelves for a user
	 * @param bookShelfRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/bookShelf", method = RequestMethod.POST,
	produces = {"application/json", "application/xml"})
	public BookShelfResponse getBookShelfForUser(@RequestBody BookShelfGetRequest bookShelfRequest){
		BookShelfResponse bookShelfResponse = new BookShelfResponse();
		List<BookShelf> listOfBookShelf = null;
		try {
			//validate request
			bookShelfResponseTransformer.validateGetRequest(bookShelfRequest);

			listOfBookShelf = bookShelfService.getBookShelf(bookShelfRequest);

			bookShelfResponse = bookShelfResponseTransformer.transformListIntoResponse(listOfBookShelf);
			bookShelfResponse.setMessage("Book Shelf retrieved successfully");
		} catch (BookBusinessServiceException e) {
			bookShelfResponse = bookShelfResponseTransformer.buildExceptionMessage(e);
		}
		return bookShelfResponse;
	}
	
}
