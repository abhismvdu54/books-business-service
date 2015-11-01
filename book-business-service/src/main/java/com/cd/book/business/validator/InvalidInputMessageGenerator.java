package com.cd.book.business.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.util.CollectionUtils;

public class InvalidInputMessageGenerator {
	/**
     * Takes messages from the provided ContraintViolation Set and puts it in
     * the validationMessage String provided
     */
    public static <T> void preparesErrorMessageFromConstraintViolations(
            final StringBuilder validationMessage,
            final Set<ConstraintViolation<T>> violations) {
        if (!CollectionUtils.isEmpty(violations)) {
            for (final ConstraintViolation<?> violation : violations) {
                validationMessage.append("\n"
                        + violation.getPropertyPath().toString() + ":"
                        + violation.getMessage() + ",");
            }
        }
    }

    /**
     * Takes messages from the provided ContraintViolation Set and puts it in
     * the validationMessage String provided, along with custom message
     */
    public static <T> void preparesErrorMessageFromConstraintViolations(
            final StringBuilder validationMessage,
            final Set<ConstraintViolation<T>> violations,
            final String customMessage) {
        if (!CollectionUtils.isEmpty(violations)) {
            for (final ConstraintViolation<?> violation : violations) {
                validationMessage.append("\nFailed validation for property : '"
                        + violation.getPropertyPath().toString()
                        + "' with value '" + violation.getInvalidValue()
                        + "', validation message : '" + violation.getMessage()
                        + "', more info : '" + customMessage + "'");
            }
        }
    }
}
