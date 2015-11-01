package com.cd.book.business.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.cd.book.business.service.OpenLibraryService;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.exception.BookBusinessServiceException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class OpenLibraryServiceImpl implements OpenLibraryService{

	RestTemplate restTemplate; 
	
	/** (non-Javadoc)
	 * @see com.cd.book.business.service.OpenLibraryService#retrieveBooksInfo(java.lang.String, int)
	 */
	@Override
	public BookSearchDTO retrieveBooksInfo(String openSearchUrl) throws BookBusinessServiceException {
		restTemplate = new RestTemplate();
		BookSearchDTO bookSearchDTO = new BookSearchDTO();
		try {
			//String searchQuery = "https://openlibrary.org/search.json?q="+subject+"&has_fulltext=true&page="+pageNo;
			String books = 	restTemplate.getForObject(openSearchUrl, String.class);

			//Parse the Json String retrieved form the open library into Java object..
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			bookSearchDTO = gson.fromJson(books, BookSearchDTO.class);
			
		} catch(HttpServerErrorException hse){

			final String serverNotAvlExMsg = " Open library Server is down";

			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), serverNotAvlExMsg);

		}catch (Exception e) {
			// request timeout error message
			final String msg = "Open library [while retrieving the book information] timed-out";
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}
		return bookSearchDTO;
	}
	/** 
	 * @see com.cd.book.business.service.OpenLibraryService#retrieveArchievedData(java.lang.String)
	 */
	@Override
	public JSONObject retrieveArchievedData(String archievedQuery) throws BookBusinessServiceException {
		String result = restTemplate.getForObject(archievedQuery, String.class);//(queryForReadableLink, String.class);
		JSONParser parser = new JSONParser();
		Object obj = new Object();
		try {
			obj = parser.parse(result);
		} catch (ParseException e) {
			
			final String msg = "Exception while parsing the archieved data";
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}
		org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject)obj;
		
		return jsonObject;
	}

}
