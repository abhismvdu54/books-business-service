package com.cd.book.mongo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.mongo.exception.BookRatingServiceException;
import com.cd.book.mongo.repository.BookRatingRepository;
import com.cd.book.mongo.response.BookRatingResponse;
import com.cd.book.mongo.service.BookRatingService;

import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

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
			throw new BookRatingServiceException(1,1,1,msg, e);
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
			throw new BookRatingServiceException(1,1,1,msg, e);
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
			throw new BookRatingServiceException(1,1,1,msg, e);
		}
		return response;
	}

	@Override
	public List<BookAvgRating> retrieveBookRatings(List<String> bookIds) throws BookRatingServiceException {

		/*try {
			List<BookRatingDocument> bookRatingDoc = bookRatingRepository.findByBookIds(bookIds);
			System.out.println("The list is: "+bookRatingDoc);

		} catch (Exception e) {
			// TODO: handle exception
		}*/
		List<BookAvgRating> listOfObject = null;
		try {

			Aggregation agg = newAggregation(
					match(Criteria.where("bookId").in(bookIds)),
					group("bookId").avg("userRating.rating").as("avgRating").sum("userRating.rating").as("sumOfRatings")


					);

			AggregationResults<BookAvgRating> groupResult = mongoTemplate.aggregate(agg, BookRatingDocument.class, BookAvgRating.class);
			listOfObject = groupResult.getMappedResults();

		} catch (Exception e) {
			throw  new BookRatingServiceException(0,0,0,"Error while getting book rating ", e);
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
			throw  new BookRatingServiceException(0,0,0,"Error while inserting book ratings ", e);
		}
		return null;
	}

}
