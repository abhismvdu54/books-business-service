package com.cd.book.business.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import com.cd.book.exception.BookBusinessServiceException;

public abstract class RequestBaseValidator {
	
		@Autowired
	    protected Validator validator;

	    protected abstract int getApplicationCode();

	    public Validator getValidator() {
	        return validator;
	    }

	    public void setValidator(Validator validator) {
	        this.validator = validator;
	    }

	    protected <T> void validateRequestDetails(T requestDetails)
	            throws BookBusinessServiceException {
	        final StringBuilder validationMessage = new StringBuilder();

	        // validate request
	        final Set<ConstraintViolation<T>> violations = validator
	                .validate(requestDetails);

	        // throw exception in case of validation failure
	        if (!CollectionUtils.isEmpty(violations)) {
	            InvalidInputMessageGenerator
	                    .preparesErrorMessageFromConstraintViolations(
	                            validationMessage, violations);
	            throw new BookBusinessServiceException(
	                    getApplicationCode(),
	                    HttpStatus.PRECONDITION_FAILED.value(),
	                    HttpStatus.PRECONDITION_FAILED.value(),
	                    validationMessage.toString());
	        }
	    }
}
