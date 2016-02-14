package com.cd.book.business.service;

import com.cd.book.business.domain.request.BookRequest;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.BooksInfoResponse;

public interface BookService {
BooksInfoResponse getBookInfoByOlids(BookRequest bookRequest) throws BookBusinessServiceException;
}
