package com.cd.book.mongo.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cd.book.constant.BookCommonConstant;
import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.mongo.exception.BookRatingServiceException;
import com.cd.book.mongo.repository.BookRatingRepository;
import com.cd.book.mongo.response.BookRatingResponse;
import com.cd.book.mongo.service.BookRatingService;

@Service
public class BookRatingServiceImpl implements BookRatingService{

	@Autowired
	private BookRatingRepository bookRatingRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public BookRatingResponse retrieveBookRating(String bookId) throws BookRatingServiceException {

		BookRatingResponse response = new BookRatingResponse();
		List<BookRatingDocument> bookRatingDocumentList = null;
		try {
			bookRatingDocumentList = bookRatingRepository.findByBookId(bookId);
			response.setBookRatingDocumentList(bookRatingDocumentList);
			double overAllBookRating = calculateOverAllRating(bookRatingDocumentList);
			response.setOverAllBookRating(overAllBookRating);
		} catch (Exception e) {
			String msg = "Exception while getting book rating";
			throw new BookRatingServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	private double calculateOverAllRating(List<BookRatingDocument> listOfRatingDoc){
		double overAllRating = 0;
		for(BookRatingDocument doc : listOfRatingDoc){
			overAllRating +=  doc.getUserRating().getRating();
		}
		overAllRating = overAllRating/(listOfRatingDoc.size());
		return overAllRating;
	}
	@Override
	public BookRatingResponse insertBookRating(BookRatingDocument bookRatingDoc) throws BookRatingServiceException {
		BookRatingResponse response = new BookRatingResponse();
		try {
			bookRatingDoc = bookRatingRepository.save(bookRatingDoc);
			response.setBookRatingDocument(bookRatingDoc);;
		} catch (Exception e) {
			String msg = "Exception while inserting book rating";
			throw new BookRatingServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	@Override
	public BookRatingResponse updateBookRating(BookRatingDocument bookRatingDoc) throws BookRatingServiceException {
		BookRatingResponse response = new BookRatingResponse();
		BookRatingDocument docFromDb = null;
		try {
			//check if it is a new record
			if(bookRatingDoc.getId() == null){
				//it's a new record
				bookRatingRepository.save(bookRatingDoc);
			}else{
				docFromDb = bookRatingRepository.findOne(bookRatingDoc.getId());

				docFromDb.setUserRating(bookRatingDoc.getUserRating());
				docFromDb = bookRatingRepository.save(docFromDb);
				response.setBookRatingDocument(docFromDb);
			}
		} catch (Exception e) {
			String msg = "Exception while updating book rating";
			throw new BookRatingServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	@Override
	public List<BookAvgRating> retrieveBookRatings(List<String> bookIds) throws BookRatingServiceException {
		List<BookAvgRating> listOfObject = null;
		try {

			Aggregation agg = newAggregation(
					match(Criteria.where("bookId").in(bookIds)),
					group("bookId").avg("userRating.rating").as("avgRating").sum("userRating.rating").as("sumOfRatings"));

			AggregationResults<BookAvgRating> groupResult = mongoTemplate.aggregate(agg, BookRatingDocument.class, BookAvgRating.class);
			listOfObject = groupResult.getMappedResults();

		} catch (Exception e) {
			String msg = "Can't retrieve book ratings";
			throw new BookRatingServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}

		return listOfObject;
	}

	@Override
	public BookRatingResponse insertBookRatings(List<BookRatingDocument> bookRatingDocs)
			throws BookRatingServiceException {
		BookRatingResponse response = new BookRatingResponse();
		try {
			List<BookRatingDocument> bookRatingDocumentResponse = bookRatingRepository.save(bookRatingDocs);
			response.setBookRatingDocumentList(bookRatingDocumentResponse);
		} catch (Exception e) {
			String msg = "Can't insert book rating)";
			throw new BookRatingServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return null;
	}

}
