package com.cd.book.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.cd.book.business.service.OpenLibraryService;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.mongo.service.BookRatingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class OpenLibraryServiceImpl implements OpenLibraryService{

	RestTemplate restTemplate; 
	@Resource
	private BookRatingService bookRatingService;

	/** (non-Javadoc)
	 * @see com.cd.book.business.service.OpenLibraryService#retrieveBooksInfo(java.lang.String, int)
	 */
	@Override
	//@Cacheable(value="booksBySubject")
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
			
			//append the book rating 
		} catch(HttpServerErrorException hse){

			final String serverNotAvlExMsg = " Open library Server is down";

			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), serverNotAvlExMsg);

		}catch (Exception e) {
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return bookSearchDTO;
	}

	/** 
	 * @see com.cd.book.business.service.OpenLibraryService#retrieveArchievedData(java.lang.String)
	 */
	@Override
	public JSONObject retrieveArchievedData(String archievedQuery) throws BookBusinessServiceException {
		restTemplate = new RestTemplate();
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
	@Override
	public JSONObject getBookInfoByOlids(List<String> olids) throws BookBusinessServiceException {
		restTemplate = new RestTemplate();
		String url = "https://openlibrary.org/api/books?bibkeys=";
		JSONParser parser = new JSONParser();
		Object obj = new Object();
		String result = null;
		for(String olid : olids){
			url+="OLID:"+olid+","; 
		}
		url += "&details=true&format=json";
		try {
			result = restTemplate.getForObject(url, String.class);
			obj = parser.parse(result);
			System.out.println("result.............  "+result);
		} catch (ParseException e) {
			String msg = "Unable to parse the json string into Json Object";
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}catch(Exception e){
			String msg = "Unable to connect with the open library service";
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}
		org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject)obj;

		return jsonObject;
	}

}
