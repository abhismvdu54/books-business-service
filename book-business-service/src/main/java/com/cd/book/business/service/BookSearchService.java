package com.cd.book.business.service;

import com.cd.book.response.SearchedBookBSResponse;

public interface BookSearchService {

	SearchedBookBSResponse searchBySubject(String subject, int pageNo);
}
