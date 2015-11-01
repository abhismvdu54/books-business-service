package com.cd.book.business.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cd.book.business.constant.CommonConstants;
import com.cd.book.business.domain.request.bookShelf.BookShelfCreateRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfGetRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfUpdateRequest;
import com.cd.book.business.service.BookShelfService;
import com.cd.book.entity.BookShelf;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.repository.BookShelfRepository;
import com.cd.book.response.transformer.BookShelfResponseTransformer;

@Component
public class BookShelfServiceImpl implements BookShelfService{

	@Resource
	BookShelfRepository bookShelfRepository;

	@Resource
	BookShelfResponseTransformer bookShelfResponseTransformer;

	/**
	 * This method adds the new book shelf
	 * @param bookShelfRequest
	 * @return
	 * @throws BookBusinessServiceException
	 */
	@Override
	public BookShelf addBookShelf(BookShelfCreateRequest bookShelfRequest) throws BookBusinessServiceException {
		BookShelf bookShelf = null;
		//populate the entity to be saved
		BookShelf bookShelfEntity = new BookShelf();
		bookShelfEntity.setActive(true);
		bookShelfEntity.setShelfName(bookShelfRequest.getBookShelfName());
		bookShelfEntity.setUserId(bookShelfRequest.getUserId());
		bookShelfEntity.setCreatedBy(bookShelfRequest.getUserEmail());
		bookShelfEntity.setCreatedOn(Calendar.getInstance());
		bookShelfEntity.setLastUpdatedOn(Calendar.getInstance());
		bookShelfEntity.setCreatedBy(bookShelfRequest.getUserEmail());
		bookShelfEntity.setLastUpdatedBy(bookShelfRequest.getUserEmail());
		try {
			//find out whether the book shelf is already present in the database or not.
			//if not present then only add it.
			bookShelf = bookShelfRepository.findByUserIdAndShelfName(bookShelfRequest.getUserId(),
					bookShelfRequest.getBookShelfName());
			//there is no entry in the table for the corresponding bookshelf for that user. So add it
			if(bookShelf == null){
				bookShelf = bookShelfRepository.save(bookShelfEntity);

			}else if(!bookShelf.isActive()){ //entry is already there but not active

				//set active as true and save it
				bookShelf.setActive(true);
				bookShelf = bookShelfRepository.save(bookShelfEntity);

			}else if(bookShelf.isActive()){//if the active flag is true then throw the error as it will be duplicate entry
				throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
						HttpStatus.NOT_ACCEPTABLE.value(), "Book Shelf already exists.");
			}
		} catch (Exception e) {
			throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while adding the record", e);
		}
		return bookShelf;
	}

	/**
	 * This method updates the book shelf with the updated book shelf name
	 * @param bookShelfRequest
	 * @return
	 * @throws BookBusinessServiceException
	 */
	@Override
	public BookShelf updateBookShelf(BookShelfUpdateRequest bookShelfRequest) throws BookBusinessServiceException {
		BookShelf bookShelf = null;

		try {
			//find out whether the book shelf is already present in the database or not.
			//if not present then only add it.
			bookShelf = bookShelfRepository.findOne(bookShelfRequest.getBookShelfId());
			//Check for the entry of the record in the table
			if(bookShelf != null){

				//check if the shelf name is already there 
				if(bookShelf.getShelfName().equalsIgnoreCase(bookShelfRequest.getBookShelfName())){
					throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
							HttpStatus.NOT_ACCEPTABLE.value(), "This book shelf is already present");
				}
				//populate the entity to be saved
				bookShelf.setShelfName(bookShelfRequest.getBookShelfName());
				bookShelf.setLastUpdatedBy(bookShelfRequest.getUserEmail());
				bookShelf.setLastUpdatedOn(Calendar.getInstance());

				bookShelf = bookShelfRepository.save(bookShelf);
			} 

			else{
				throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
						HttpStatus.NOT_ACCEPTABLE.value(), "There is no record with this book shelf name");
			}
		}catch (BookBusinessServiceException e) {
			throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, e.getCode(),e.getStatus(), 
					e.getExceptionMessage(), e);
		}catch(Exception e){
			throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while updating the record", e);
		}
		return bookShelf;
	}

	/**
	 * This method  soft deletes the book shelf from the database
	 * @param bookShelfRequest
	 * @return
	 * @throws BookBusinessServiceException
	 */
	
	@Override
	public BookShelf deleteBookShelf(BookShelfUpdateRequest bookShelfRequest) throws BookBusinessServiceException {
		BookShelf bookShelf = null;
		try{
			//find out whether the book shelf is already present in the database or not.
			//if not present then only add it.
			bookShelf = bookShelfRepository.findOne(bookShelfRequest.getBookShelfId());
			//Check for the entry of the record in the table
			if(bookShelf != null){
				//check if the book shelf has already been deleted
				if(!bookShelf.isActive()){
					throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
							HttpStatus.NOT_ACCEPTABLE.value(), "Book Shelf has already been deleted");
				}else{
					//populate the entity
					bookShelf.setActive(false);
					bookShelf.setLastUpdatedBy(bookShelfRequest.getUserEmail());
					bookShelf.setLastUpdatedOn(Calendar.getInstance());
					bookShelf = bookShelfRepository.save(bookShelf);
				}
				
			}else{
				throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.NOT_ACCEPTABLE.value(), 
						HttpStatus.NOT_ACCEPTABLE.value(), "There is no record with this book shelf name");
			}
		}catch(BookBusinessServiceException bse){
			throw new BookBusinessServiceException(bse.getApplicationCode(), bse.getCode(),
					bse.getStatus(),bse.getExceptionMessage());
		}catch(Exception e){
			throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while deleting the record", e);
		}
		return bookShelf;
	}

	/** This fetches all the records for a user

	 ** @see com.cd.book.business.service.BookShelfService#getBookShelf(com.cd.book.business.domain.request.bookShelf.BookShelfGetRequest)
	 */
	@Override
	public List<BookShelf> getBookShelf(BookShelfGetRequest bookShelfRequest) throws BookBusinessServiceException {
		
		List<BookShelf> listOfBookShelf = new ArrayList<BookShelf>();
		try {
			listOfBookShelf = bookShelfRepository.findByUserId(bookShelfRequest.getUserId());
			
		} catch (Exception e) {
			throw new BookBusinessServiceException(CommonConstants.APPLICATION_CODE_BOOK_SERVICE, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
					HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while fetching the book shelf", e);
		}
		return listOfBookShelf;
	}

}
