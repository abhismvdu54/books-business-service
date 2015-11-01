package com.cd.book.business.service;

import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.GoodReadRatingBook;

public interface GoodReadService {

	GoodReadRatingBook retrieveBookRating(String goodReadQuery) throws BookBusinessServiceException;
}
