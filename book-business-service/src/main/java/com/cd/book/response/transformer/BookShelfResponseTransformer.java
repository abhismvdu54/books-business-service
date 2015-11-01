package com.cd.book.response.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cd.book.business.constant.CommonConstants;
import com.cd.book.business.domain.request.bookShelf.BookShelfCreateRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfGetRequest;
import com.cd.book.business.domain.request.bookShelf.BookShelfUpdateRequest;
import com.cd.book.business.validator.RequestBaseValidator;
import com.cd.book.dto.BookShelfDTO;
import com.cd.book.entity.BookShelf;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.BookShelfResponse;

@Component
public class BookShelfResponseTransformer extends RequestBaseValidator{

	public BookShelfResponse buildExceptionMessage(BookBusinessServiceException e){
		BookShelfResponse response = new BookShelfResponse();
		response.setApplicationCode(CommonConstants.APPLICATION_CODE_BOOK_SERVICE);
		response.setCode(e.getCode());
		response.setStatus(e.getStatus());
		if(e.getCause() != null){
		response.setDeveloperMessage(e.getCause().getLocalizedMessage());
		}
		response.setMessage(e.getMessage());
		return response;
	}
	private void buildSuccessMessage(BookShelfResponse response){
		response.setCode(HttpStatus.OK.value());
		response.setApplicationCode(CommonConstants.APPLICATION_CODE_BOOK_SERVICE);
		response.setStatus(HttpStatus.OK.value());
		
	}
	public BookShelfResponse transformIntoResponse(BookShelf bookShelf){
		BookShelfResponse response = new BookShelfResponse();
		BookShelfDTO bookShelfDTO = new BookShelfDTO();
		if(bookShelf !=null){
			bookShelfDTO.setBookShelfId(bookShelf.getBookShelfId());
			bookShelfDTO.setBookShelfName(bookShelf.getShelfName());
		}
		response.setBookShelf(bookShelfDTO);
		buildSuccessMessage(response);
		return response;
	}
	
	public BookShelfResponse transformListIntoResponse(List<BookShelf> listOfBookShelf){
		List<BookShelfDTO> listOfBookShelfDTO = new ArrayList<BookShelfDTO>();
		BookShelfResponse response = new BookShelfResponse();
		for(BookShelf bookShelf : listOfBookShelf){
			BookShelfDTO dto = new BookShelfDTO();
			dto.setBookShelfId(bookShelf.getBookShelfId());
			dto.setBookShelfName(bookShelf.getShelfName());
			listOfBookShelfDTO.add(dto);
		}
		buildSuccessMessage(response);
		response.setListOfBookShelf(listOfBookShelfDTO);
		return response;
	}
	public void validateCreateRequest(BookShelfCreateRequest request) throws BookBusinessServiceException{
		validateRequestDetails(request);
	}
	
	public void validateUpdateRequest(BookShelfUpdateRequest request) throws BookBusinessServiceException{
		validateRequestDetails(request);
	}
	
	public void validateGetRequest(BookShelfGetRequest request) throws BookBusinessServiceException{
		validateRequestDetails(request);
	}
	
	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return CommonConstants.APPLICATION_CODE_BOOK_SERVICE;
	}
}
