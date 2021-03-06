package com.cd.book.mongo.exception;

import com.cd.book.exception.BaseBusinessServiceException;

@SuppressWarnings("serial")
public class BookRatingServiceException extends BaseBusinessServiceException{
	public BookRatingServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public BookRatingServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
