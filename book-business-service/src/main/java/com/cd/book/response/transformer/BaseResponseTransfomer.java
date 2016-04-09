package com.cd.book.response.transformer;

import org.springframework.http.HttpStatus;

import com.cd.book.exception.BaseBusinessServiceException;
import com.cd.book.response.BaseResponse;

/**
 * @author pandea9
 *
 */
public abstract class BaseResponseTransfomer {

	/**
	 * This method sets the exception into response object's properties.
	 * @param serviceResponse
	 * @param e
	 */
	public BaseResponse	buildExceptionResponse(BaseBusinessServiceException e,BaseResponse baseResponse){
		baseResponse.setApplicationCode(2);
		baseResponse.setCode(e.getCode());
		baseResponse.setStatus(e.getStatus());
		if(e.getCause() != null){
			baseResponse.setDeveloperMessage(e.getCause().getLocalizedMessage());
		}
		baseResponse.setMessage(e.getMessage());
		return baseResponse;
	}
	
	public BaseResponse transformIntoSuccessResponse(BaseResponse response){
		buildSuccessMessage(response);
		return response;
	}
	public void buildSuccessMessage(BaseResponse response){
		response.setCode(HttpStatus.OK.value());
		response.setApplicationCode(2);
		response.setStatus(HttpStatus.OK.value());
		response.setMessage("Success");
	}
	
	protected abstract int getApplicationCode();
	
}
