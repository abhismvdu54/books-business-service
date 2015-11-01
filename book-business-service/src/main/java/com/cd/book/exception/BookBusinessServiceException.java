package com.cd.book.exception;

@SuppressWarnings("serial")
public class BookBusinessServiceException extends BaseBusinessServiceException{

	public BookBusinessServiceException(int applicationCode, int code, int status, String exceptionMessage) {
		super(applicationCode, code, status, exceptionMessage);
	}
	public BookBusinessServiceException(int applicationCode, int code, int status, String exceptionMessage, Throwable t) {
		super(applicationCode, code, status, exceptionMessage, t);
	}
}
