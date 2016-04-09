package com.cd.book.mongo.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cd.book.constant.BookCommonConstant;
import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.document.BookReviewDocument;
import com.cd.book.mongo.domain.UserComment;
import com.cd.book.mongo.dto.request.AddBookReviewCommentReq;
import com.cd.book.mongo.dto.request.UpdateBookReviewCommentReq;
import com.cd.book.mongo.exception.BookReviewServiceException;
import com.cd.book.mongo.repository.BookReviewRepository;
import com.cd.book.mongo.response.BookRatingResponse;
import com.cd.book.mongo.response.BookReviewResponse;
import com.cd.book.mongo.service.BookRatingService;
import com.cd.book.mongo.service.BookReviewService;
import com.cd.book.util.NumberFormatter;

@Service
public class BookReviewServiceImpl implements BookReviewService {

	@Autowired
	private BookReviewRepository bookReviewRepository;
	
	@Resource
	private BookRatingService bookRatingService;

	@Override
	public BookReviewResponse retrieveBookReview(String bookId) throws BookReviewServiceException {
		List<BookReviewDocument> bookReviewDocList = null;
		BookReviewResponse response = new BookReviewResponse();
		try {
			bookReviewDocList = bookReviewRepository.findByBookId(bookId);
			
			
			//call the book rating service to get the book rating for the particular book
			BookRatingResponse bookRatingResponse = bookRatingService.retrieveBookRating(bookId);
			double overAllBookRating = calculateOverAllRating(bookRatingResponse.getBookRatingDocumentList());
			//populate the user rating into the resposne
			populateUserRatingForBook(bookReviewDocList, bookRatingResponse.getBookRatingDocumentList());
			
			//set the response
			response.setBookReviewDocumentList(bookReviewDocList);
			response.setOverAllBookRating(NumberFormatter.formatDouble(overAllBookRating));
			response.setTotalRatings(bookRatingResponse.getBookRatingDocumentList().size());
			response.setBookRatingDocumentList(bookRatingResponse.getBookRatingDocumentList());
		} catch (Exception e) {
			String msg = "Can't get book review";
			throw new BookReviewServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	private void populateUserRatingForBook(List<BookReviewDocument> bookReviewDocList, List<BookRatingDocument> bookRatingDocList){
		for(BookReviewDocument bookReviewDoc : bookReviewDocList){
			for(BookRatingDocument bookRatingDoc : bookRatingDocList){
				if(bookReviewDoc.getUser().getUserEmail().equalsIgnoreCase(bookRatingDoc.getUserRating().getUser().getUserEmail())){
					bookReviewDoc.setBookRatingDocument(bookRatingDoc);
					break;
				}
			}
		}
	}
	private double calculateOverAllRating(List<BookRatingDocument> listOfRatingDoc){
		double overAllRating = 0;
		for(BookRatingDocument doc : listOfRatingDoc){
			overAllRating +=  doc.getUserRating().getRating();
		}
		if(listOfRatingDoc.size() != 0){
		overAllRating = overAllRating/listOfRatingDoc.size();
		}
		return overAllRating;
	}
	
	@Override
	public BookReviewResponse insertBookReview(BookReviewDocument bookReviewDocument)
			throws BookReviewServiceException {
		BookReviewDocument bookReviewDoc = null;
		BookReviewResponse response = new BookReviewResponse();
		try {
			bookReviewDoc = bookReviewRepository.save(bookReviewDocument);
			response.setBookReviewDocument(bookReviewDoc);
		} catch (Exception e) {
			String msg = "Exception while inserting new book review";
			throw new BookReviewServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		
		return response;
	}

	@Override
	public BookReviewResponse updateBookReview(BookReviewDocument bookReviewDocument)
			throws BookReviewServiceException {
		BookReviewDocument bookReviewDocFromDB = null;
		BookReviewResponse response = new BookReviewResponse();
		try {
			//retrieve the data corresponding to the id
			bookReviewDocFromDB = bookReviewRepository.findOne(bookReviewDocument.getId());
			
			//update the data to be saved
			bookReviewDocFromDB.setUser(bookReviewDocument.getUser());
			bookReviewDocFromDB.setReview(bookReviewDocument.getReview());
			bookReviewDocFromDB.setBookRatingDocument(bookReviewDocument.getBookRatingDocument());
			bookReviewDocFromDB.setLikedBy(bookReviewDocument.getLikedBy());
			bookReviewDocFromDB.setLikes(bookReviewDocument.getLikes());
			bookReviewDocFromDB.setUserComments(bookReviewDocument.getUserComments());
			//save the updated user reviews
			bookReviewDocFromDB = bookReviewRepository.save(bookReviewDocFromDB);
			response.setBookReviewDocument(bookReviewDocFromDB);
		} catch (Exception e) {
			String msg = "Exception while inserting new book review";
			throw new BookReviewServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	@Override
	public BookReviewResponse addBookReviewComment(AddBookReviewCommentReq bookReviewCommentReq)
			throws BookReviewServiceException {
		
		BookReviewDocument bookReviewDocFromDB = null;
		BookReviewResponse response = new BookReviewResponse();
		
		try {
			//retrieve the data corresponding to the id
			bookReviewDocFromDB = bookReviewRepository.findOne(bookReviewCommentReq.getBookReviewId());
			if(bookReviewDocFromDB !=null ){
				List<UserComment> userComments = bookReviewDocFromDB.getUserComments();
				if(userComments == null){
					userComments = new ArrayList<UserComment>();
				}
				userComments.add(bookReviewCommentReq.getUserComment());
				bookReviewDocFromDB.setUserComments(userComments);
				//save the document with updated data
				bookReviewDocFromDB = bookReviewRepository.save(bookReviewDocFromDB);
				response.setBookReviewDocument(bookReviewDocFromDB);
			}
		} catch (Exception e) {
			String msg = "Can't add user comment";
			throw new BookReviewServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}

	/**
	 * this method is for updating the review comment on the book review
	 * @see com.cd.book.mongo.service.BookReviewService#updateBookReviewComment(com.cd.book.mongo.dto.request.UpdateBookReviewCommentReq)
	 */
	@Override
	public BookReviewResponse updateBookReviewComment(UpdateBookReviewCommentReq bookReviewCommentReq)
			throws BookReviewServiceException {
		
		BookReviewDocument bookReviewDocFromDB = null;
		BookReviewResponse response = new BookReviewResponse();
		try {
			bookReviewDocFromDB = bookReviewRepository.findOne(bookReviewCommentReq.getBookReviewId());
			//find out the user comment from the request
			UserComment userCommentFromReq = bookReviewCommentReq.getUserComment();
			List<UserComment> userComments = bookReviewCommentReq.getUserComments();
			int index = bookReviewCommentReq.getUserCommentIndex();//userComments.indexOf(userCommentFromReq);
			//update the user comment with the date and set it to the user comments
			//todo...set date in the user comment
			userCommentFromReq.setDate(Calendar.getInstance());
			//set the updated user comment in the user comments
			userComments.set(index, userCommentFromReq);
			
			bookReviewDocFromDB.setUserComments(userComments);
			
			//save the document
			bookReviewDocFromDB = bookReviewRepository.save(bookReviewDocFromDB);
			response.setBookReviewDocument(bookReviewDocFromDB);
			
		} catch (Exception e) {
			String msg = "Can't update user comment";
			throw new BookReviewServiceException(BookCommonConstant.APPLICATION_CODE,HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(),msg, e);
		}
		return response;
	}


}
