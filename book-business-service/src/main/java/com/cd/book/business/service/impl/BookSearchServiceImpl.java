package com.cd.book.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.cd.book.business.domain.request.BookSearchCriteriaRequest;
import com.cd.book.business.service.BookSearchService;
import com.cd.book.business.service.GoodReadService;
import com.cd.book.business.service.OpenLibraryService;
import com.cd.book.dto.BookInfo;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.mongo.service.BookRatingService;
import com.cd.book.repository.BookRepository;
import com.cd.book.response.SearchedBookBSResponse;
import com.cd.book.response.transformer.BookSearchTransformer;

/**
 * @author pandea9
 *
 */
@Service
public class BookSearchServiceImpl implements BookSearchService{

	RestTemplate restTemplate = new RestTemplate();

	@Resource
	private BookRatingService bookRatingService;
	
	@Resource
	BookRepository bookRepository;
	
	@Resource
	BookSearchTransformer bookSearchTransformer; 

	@Resource
	OpenLibraryService openLibraryService; 

	@Resource
	GoodReadService goodReadService;

	/**
	 * This searches the book from open library and send the response with
	 * readable links, good read ratings
	 */
	@Override
	public SearchedBookBSResponse searchBooks(BookSearchCriteriaRequest bookSearchCriteria, String pageNo) throws BookBusinessServiceException{

		//retrieve the books information from open library
		SearchedBookBSResponse searchedBookBSResponse = null;

		//make the open library search URL
		String openLibSearchUrl = makeOpenLibrarySearchUrl(bookSearchCriteria, pageNo);
		//call the open library service to get the books info and transform into response
		searchedBookBSResponse = callNTransformBooksDataIntoResponse(openLibSearchUrl);

		return searchedBookBSResponse;
	}

	private SearchedBookBSResponse callNTransformBooksDataIntoResponse(String openLibSearchUrl) throws BookBusinessServiceException{
		SearchedBookBSResponse searchedBookBSResponse = null;
		BookSearchDTO bookSearchDTO = openLibraryService.retrieveBooksInfo(openLibSearchUrl);
		List<BookAvgRating> listOfRatingDoc = null;
		//call the bookRating Service to retrieve the list of ratings for the list of ia.
		List<String> ias = new ArrayList<String>();
		for(int i=0; i<bookSearchDTO.getDocs().size();i++){
			ias.add(bookSearchDTO.getDocs().get(i).getIa()[0]);
		}
		try {
			listOfRatingDoc = bookRatingService.retrieveBookRatings(ias);
		} catch (Exception e) {
			
			searchedBookBSResponse = bookSearchTransformer.transformIntoReadableNBorrowable(bookSearchDTO);
			bookSearchTransformer.setSuccessMessage(searchedBookBSResponse);
			searchedBookBSResponse.setDeveloperMessage("Unable to populate the good read rating, reason : "+ e);
			searchedBookBSResponse.setMessage("Problem loading the rating of the books");
			return searchedBookBSResponse;
		}
		
		//convert the list into map
		Map<String, BookAvgRating> ratingMap = convertAvgRatingListIntoMap(listOfRatingDoc);
		//traverse through the list of books and add the rating for that from map
		addBookRatingFromMap(bookSearchDTO.getDocs(), ratingMap);

		//divide the total no of books into Readable and Borrowable list
		searchedBookBSResponse = bookSearchTransformer.transformIntoReadableNBorrowable(bookSearchDTO);
		bookSearchTransformer.setSuccessMessage(searchedBookBSResponse);

		return searchedBookBSResponse;
	}

	private void addBookRatingFromMap(List<BookInfo> bookInfoList, Map<String, BookAvgRating> bookRatingMap){
		double defaultBookRating = 3;
		for(BookInfo bookInfo : bookInfoList){
			String bookId = bookInfo.getIa()[0];
			BookAvgRating bookRating = bookRatingMap.get(bookId);
			//check if the book rating is null or 0. It means no one has rated so far. So put the default one.
			if(bookRating == null || bookRating.getAvgRating() == 0 ){
				//put the default value of the rating
				bookInfo.setBookRating(defaultBookRating);
				bookInfo.setNoOfRatings(1);
			}else{
					bookInfo.setBookRating(bookRating.getAvgRating());
					//total no of ratings will be sum/avg_rating
					bookInfo.setNoOfRatings(bookRating.getSumOfRatings()/bookRating.getAvgRating());
				}
			}
	}
	private Map<String, BookAvgRating> convertAvgRatingListIntoMap(List<BookAvgRating> listOfRatingDoc){
		Map<String, BookAvgRating> map = new HashMap<String, BookAvgRating>();
		for(BookAvgRating bookAvgRating : listOfRatingDoc){
			map.put(bookAvgRating.get_id(), bookAvgRating);
		}
		return map;
	}
	
	/**
	 * This method makes the url for the open library to search the books based on the input
	 * @param bookSearchCriteria
	 * @return
	 */
	private String makeOpenLibrarySearchUrl(BookSearchCriteriaRequest bookSearchCriteria, String pageNo){
		String searchUrl = "http://openlibrary.org/search.json?";
		//check which all properties are not null and put into the search criteria url
		if(!StringUtils.isEmpty(bookSearchCriteria.getSubject())){
			searchUrl += "subject="+bookSearchCriteria.getSubject()+"&";
		}
		if(!StringUtils.isEmpty(bookSearchCriteria.getAuthor())){
			searchUrl += "author="+bookSearchCriteria.getAuthor()+"&";
		}
		if(!StringUtils.isEmpty(bookSearchCriteria.getTitle())){
			searchUrl += "title="+bookSearchCriteria.getTitle()+"&";
		}
		if(!StringUtils.isEmpty(bookSearchCriteria.getPublisher())){
			searchUrl += "publisher="+bookSearchCriteria.getPublisher()+"&";
		}
		if(!StringUtils.isEmpty(bookSearchCriteria.getPlace())){
			searchUrl += "place="+bookSearchCriteria.getPlace()+"&";
		}
		if(!StringUtils.isEmpty(bookSearchCriteria.getIsbn())){
			searchUrl += "isbn="+bookSearchCriteria.getIsbn()+"&";
		}
		searchUrl += "&has_fulltext=true&page="+pageNo;
		//do the changes for publish year
		return searchUrl;

	}

	@Override
	//@Cacheable(value="booksBySubject")
	public SearchedBookBSResponse searchBooksBySubject(String subject, String pageNo)
			throws BookBusinessServiceException {
		String searchUrl = "http://openlibrary.org/search.json?q="+subject+"&page="+pageNo+"&has_fulltext=true";
		//call the open library service to get the books info and transform into resposne
		SearchedBookBSResponse searchedBookBSResponse = callNTransformBooksDataIntoResponse(searchUrl);
		return searchedBookBSResponse;
	}

}
