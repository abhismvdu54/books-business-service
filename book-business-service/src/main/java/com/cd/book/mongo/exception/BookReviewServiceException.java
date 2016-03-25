package com.cd.book.mongo.exception;

import com.cd.book.exception.BaseBusinessServiceException;

@SuppressWarnings("serial")
public class BookReviewServiceException extends BaseBusinessServiceException{

	public BookReviewServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public BookReviewServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
