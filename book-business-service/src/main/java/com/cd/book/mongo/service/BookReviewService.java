package com.cd.book.mongo.service;

import com.cd.book.mongo.document.BookReviewDocument;
import com.cd.book.mongo.dto.request.AddBookReviewCommentReq;
import com.cd.book.mongo.dto.request.UpdateBookReviewCommentReq;
import com.cd.book.mongo.exception.BookReviewServiceException;
import com.cd.book.mongo.response.BookReviewResponse;

/**
 * This interface provides the methods for book review related operations
 * @author pandea9
 *
 */
public interface BookReviewService {

	BookReviewResponse retrieveBookReview(String bookId) throws BookReviewServiceException;
	BookReviewResponse insertBookReview(BookReviewDocument bookReviewDocument) throws BookReviewServiceException;
	BookReviewResponse updateBookReview(BookReviewDocument bookReviewDocument) throws BookReviewServiceException;
	BookReviewResponse addBookReviewComment(AddBookReviewCommentReq bookReviewCommentReq) throws BookReviewServiceException;
	BookReviewResponse updateBookReviewComment(UpdateBookReviewCommentReq bookReviewCommentReq) throws BookReviewServiceException;

}
