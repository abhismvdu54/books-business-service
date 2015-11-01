package com.cd.book.business.service;

import java.util.List;

import com.cd.book.business.domain.request.bookShelf.BookShelfCreateRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfGetRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfUpdateRequest;
import com.cd.book.entity.BookShelf;
import com.cd.book.exception.BookBusinessServiceException;

public interface BookShelfService {

	BookShelf addBookShelf(BookShelfCreateRequest bookShelfRequest) 
			throws BookBusinessServiceException;
	
	BookShelf updateBookShelf(BookShelfUpdateRequest bookShelfRequest) 
			throws BookBusinessServiceException;
	
	BookShelf deleteBookShelf(BookShelfUpdateRequest bookShelfRequest) 
			throws BookBusinessServiceException;
	
	List<BookShelf> getBookShelf(BookShelfGetRequest bookShelfRequest)
			throws BookBusinessServiceException;
}
