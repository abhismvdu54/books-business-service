package com.cd.book.mongo.service;

import java.util.List;

import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.mongo.exception.BookRatingServiceException;
import com.cd.book.mongo.response.BookRatingResponse;

public interface BookRatingService {

	BookRatingResponse retrieveBookRating(String bookId) throws BookRatingServiceException;
	BookRatingResponse insertBookRating(BookRatingDocument bookRatingDoc) throws BookRatingServiceException;
	BookRatingResponse updateBookRating(BookRatingDocument bookRatingDoc) throws BookRatingServiceException;
	List<BookAvgRating> retrieveBookRatings(List<String> bookIds) throws BookRatingServiceException;
	BookRatingResponse insertBookRatings(List<BookRatingDocument> bookRatingDocs) throws BookRatingServiceException;
}
