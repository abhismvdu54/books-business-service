package com.cd.book.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cd.book.business.domain.request.BookRequest;
import com.cd.book.business.service.BookService;
import com.cd.book.business.service.OpenLibraryService;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.BooksInfoResponse;
import com.cd.book.response.transformer.BookInfoResponseTransformer;

@Component
public class BookServiceImpl implements BookService{

	@Resource
	OpenLibraryService openLibraryService;
	
	@Resource
	BookInfoResponseTransformer bookInfoResponseTransformer; 
	@Override
	public BooksInfoResponse getBookInfoByOlids(BookRequest bookRequest) throws BookBusinessServiceException {
		
		List<String> listOfOlids = bookRequest.getOlids();
		org.json.simple.JSONObject result = openLibraryService.getBookInfoByOlids(listOfOlids);
		BooksInfoResponse response = bookInfoResponseTransformer.transformIntoBookResponse(result, listOfOlids);
		return response;
	}

}
