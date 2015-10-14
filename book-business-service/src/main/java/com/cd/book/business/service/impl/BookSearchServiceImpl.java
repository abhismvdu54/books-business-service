package com.cd.book.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cd.book.business.service.BookSearchService;
import com.cd.book.dto.BookInfo;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.dto.GoodReadReviewRating;
import com.cd.book.entity.GoodReadRatingSearchedEntity;
import com.cd.book.response.SearchedBookBSResponse;
import com.cd.book.response.transformer.BookSearchTransformer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class BookSearchServiceImpl implements BookSearchService{

	RestTemplate restTemplate = new RestTemplate();
	BookSearchTransformer bookSearchTransformer = new BookSearchTransformer(); 
	@Override
	@Cacheable(value="booksBySubject")
	public SearchedBookBSResponse searchBySubject(String subject, int pageNo) {

		//retrieve the books information from open library
		SearchedBookBSResponse searchedBookBSResponse = new SearchedBookBSResponse();
		BookSearchDTO bookSearchDTO = new BookSearchDTO();
		String searchQuery = "http://openlibrary.org/search.json?q="+subject+"&has_fulltext=true&page="+pageNo;
		String books = 	restTemplate.getForObject(searchQuery, String.class);
		
		//Parse the Json String retrieved form the open library into Java object..
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		bookSearchDTO = gson.fromJson(books, BookSearchDTO.class);

		//make the query for readable archieved links and Good read links in to a map
		Map<String, String> mapOfQueries =  makeQueryForLinks(bookSearchDTO);

		//populate into booksearchdto with all the properties of archieved data and the good read data
		try {
			BookSearchDTO bookSearchDToFinal = populateDataIntoBookSearchDTO(mapOfQueries, bookSearchDTO);

			//divide the total no of books into Readable and Borrowable list
			searchedBookBSResponse = bookSearchTransformer.transformIntoReadableNBorrowable(bookSearchDToFinal);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return searchedBookBSResponse;

	}
	
	//this method is for populating the archieved data and the good read data etc into out booksearchdto.
	private BookSearchDTO populateDataIntoBookSearchDTO(Map<String, String> mapOfQueries, BookSearchDTO bookSearchDTO) throws ParseException{

		String booksInfoQuery = mapOfQueries.get("booksInfoQuery");
		String goodReadReviewQuery = mapOfQueries.get("goodReadReviewQuery");

		String result = restTemplate.getForObject(booksInfoQuery, String.class);//(queryForReadableLink, String.class);

		//call the query for good read rating to fetch all the rating corresponding to isbns.
		String goodReadReviewResponse = restTemplate.getForObject(goodReadReviewQuery, String.class);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		GoodReadRatingSearchedEntity goodReadEntity = gson.fromJson(goodReadReviewResponse, GoodReadRatingSearchedEntity.class);

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(result);
		org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject)obj;
		try {
			int bookWithIsbnCount = -1;
			for(BookInfo bookInfo : bookSearchDTO.getDocs()){

				String toSearch = null;
				//check for ISBN and set in URL if present
				if(bookInfo.getIsbn() != null && bookInfo.getIsbn().length >0){
					toSearch = "ISBN:"+ bookInfo.getIsbn()[0];
					bookWithIsbnCount++;
					
					//set the good read rating object for all the books for which we have got the good read rating according to isbns
					GoodReadReviewRating gRating = new GoodReadReviewRating();
					gRating.setAverageRating(goodReadEntity.getBooks().get(bookWithIsbnCount).getAverage_rating());
					gRating.setWorkRatingsCount(goodReadEntity.getBooks().get(bookWithIsbnCount).getWork_ratings_count());
					gRating.setRatingsCount(goodReadEntity.getBooks().get(bookWithIsbnCount).getReviews_count());
					gRating.setTextReviewsCount(goodReadEntity.getBooks().get(bookWithIsbnCount).getText_reviews_count());
					gRating.setWorkReviewsCount(goodReadEntity.getBooks().get(bookWithIsbnCount).getWork_reviews_count());
					gRating.setReviewsCount(goodReadEntity.getBooks().get(bookWithIsbnCount).getReviews_count());
					
					bookInfo.setGoodReadReviewRating(gRating);

				}
				//check for lccn and Set in the URL If Present
				else if(bookInfo.getLccn() != null && bookInfo.getLccn().length >0){
					toSearch = "LCCN:"+ bookInfo.getLccn()[0];
				}
				//check for oclc and Set in the URL If Present
				else if(bookInfo.getOclc() != null && bookInfo.getOclc().length >0){
					toSearch = "OCLC:"+bookInfo.getOclc()[0];
				}
				if(!StringUtils.isBlank(toSearch)) {
					org.json.simple.JSONObject j = (org.json.simple.JSONObject) jsonObject.get(toSearch);
					String thumbUrl = (String)j.get("thumbnail_url");
					String preview = (String)j.get("preview");
					String previewUrl = (String)j.get("preview_url");
					String infoUrl = (String)j.get("info_url");
					bookInfo.setThumbnailUrl(thumbUrl);
					bookInfo.setPreview(preview);
					bookInfo.setPreviewUrl(previewUrl);
					bookInfo.setInfoUrl(infoUrl);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return bookSearchDTO;
	}


	//this is for the /books/bibkeys?  rest api

	private Map<String, String> makeQueryForLinks(BookSearchDTO allBooks){

		Map<String, String> queryMap = new HashMap<String, String>();
		//make the query based on 'isbn', 'lccn', 'oclc', 'olid', 'iaid', 'bibkeys'
		// API query url
		String goodReadUrlForReview = "https://www.goodreads.com/book/review_counts.json?key=tsynJ8Pv97fhb87wiVu0rA&isbns=";
		String q = "http://openlibrary.org/api/books?bibkeys=";


		//loop through the list
		for(BookInfo bookInfo : allBooks.getDocs()){

			//check for ISBN and set in URL if present
			if(bookInfo.getIsbn() != null && bookInfo.getIsbn().length >0){
				q += "ISBN:"+ bookInfo.getIsbn()[0]+",";
				goodReadUrlForReview += bookInfo.getIsbn()[0]+",";
			}
			//check for lccn and Set in the URL If Present
			else if(bookInfo.getLccn() != null && bookInfo.getLccn().length >0){
				q += "LCCN:"+ bookInfo.getLccn()[0]+",";
			}
			//check for oclc and Set in the URL If Present
			else if(bookInfo.getOclc() != null && bookInfo.getOclc().length >0){
				q += "OCLC:"+bookInfo.getOclc()[0]+",";
			}
		}

		queryMap.put("booksInfoQuery", q+"&format=json");
		queryMap.put("goodReadReviewQuery", goodReadUrlForReview);
		return queryMap;
	}

}
