package com.cd.book.business.service;

import org.json.simple.JSONObject;

import com.cd.book.dto.BookSearchDTO;
import com.cd.book.exception.BookBusinessServiceException;

public interface OpenLibraryService {

	BookSearchDTO retrieveBooksInfo(String openSearchUrl) throws BookBusinessServiceException;
	JSONObject retrieveArchievedData(String archievedQuery) throws BookBusinessServiceException;
}
