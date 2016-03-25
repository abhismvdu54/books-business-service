package com.cd.book.exception;
/**
 * @author pandea9
 * This is a base exception class which needs to be extended by each of the sub exception classes
 */
public class BaseBusinessServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	 // Code Particular to the application
    private int applicationCode;

    // Code Particular to the service
    private int code;

    private String exceptionMessage;

    private int status;

    public BaseBusinessServiceException(int applicationCode, int code,
            int status, String exceptionMessage) {
        super(exceptionMessage);
        this.applicationCode = applicationCode;
        this.code = code;
        this.setStatus(status);
        this.exceptionMessage = exceptionMessage;
    }

    public BaseBusinessServiceException(int applicationCode, int code,
            int status, String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
        this.applicationCode = applicationCode;
        this.code = code;
        this.setStatus(status);
        this.exceptionMessage = exceptionMessage;
    }

    public BaseBusinessServiceException(String exceptionMessage, Throwable cause) {
        super(exceptionMessage, cause);
    }

    public int getApplicationCode() {
        return applicationCode;
    }

    public int getCode() {
        return code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
