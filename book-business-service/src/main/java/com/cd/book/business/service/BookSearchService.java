package com.cd.book.business.service;

import com.cd.book.business.domain.request.BookSearchCriteriaRequest;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.SearchedBookBSResponse;

public interface BookSearchService {

	SearchedBookBSResponse searchBooks(BookSearchCriteriaRequest bookSearchCriteria, String pageNo) throws BookBusinessServiceException;
	SearchedBookBSResponse searchBooksBySubject(String subject, String pageNo) throws BookBusinessServiceException;
}
