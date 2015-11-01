package com.cd.book.response.transformer;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cd.book.exception.BaseBusinessServiceException;
import com.cd.book.response.CommonBaseResponse;

/**
 * @author pandea9
 *
 */
@Component
public class BookBusinessServiceExceptionTransformer {

	/**
	 * This method sets the exception into response object's properties.
	 * @param serviceResponse
	 * @param e
	 */
	public <T extends CommonBaseResponse> void buildServiceExceptionResponse(
            T serviceResponse, Exception e) {

        final Throwable cause = e.getCause();

        if (cause instanceof BaseBusinessServiceException) {
            final BaseBusinessServiceException exception = (BaseBusinessServiceException) cause;
            serviceResponse.setMessage(exception.getExceptionMessage());
            serviceResponse.setStatus(exception.getStatus());
            serviceResponse.setCode(exception.getCode());
            serviceResponse.setApplicationCode(exception.getApplicationCode());
        } else if (cause != null && cause.getCause() != null
                && cause.getCause() instanceof BaseBusinessServiceException) {
            final BaseBusinessServiceException exception = (BaseBusinessServiceException) cause
                    .getCause();
            serviceResponse.setMessage(exception.getExceptionMessage());
            serviceResponse.setStatus(exception.getStatus());
            serviceResponse.setCode(exception.getCode());
            serviceResponse.setApplicationCode(exception.getApplicationCode());
        } else {
            // Setting the Server error HTTP status code.
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            // Setting the Server error code specific to the service
            serviceResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

            if (cause != null) {
                serviceResponse.setMessage(cause.getMessage());
            }
        }

        if (cause != null) {
            serviceResponse.setDeveloperMessage(ExceptionUtils
                    .getStackTrace(cause));
        }
    }
}
