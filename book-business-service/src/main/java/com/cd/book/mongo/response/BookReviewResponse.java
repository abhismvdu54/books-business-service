package com.cd.book.mongo.response;

import java.util.List;

import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.mongo.document.BookReviewDocument;
import com.cd.book.response.BaseResponse;

public class BookReviewResponse extends BaseResponse{
	//This is for the purpose of inserting the book review document
private BookReviewDocument bookReviewDocument;
//This is for the purpose of retrieving the book review documents for a book
private List<BookReviewDocument> bookReviewDocumentList;
private List<BookRatingDocument> bookRatingDocumentList;
private double overAllBookRating;
private int totalRatings;

public BookReviewDocument getBookReviewDocument() {
	return bookReviewDocument;
}

public void setBookReviewDocument(BookReviewDocument bookReviewDocument) {
	this.bookReviewDocument = bookReviewDocument;
}

public List<BookReviewDocument> getBookReviewDocumentList() {
	return bookReviewDocumentList;
}

public void setBookReviewDocumentList(List<BookReviewDocument> bookReviewDocumentList) {
	this.bookReviewDocumentList = bookReviewDocumentList;
}

public double getOverAllBookRating() {
	return overAllBookRating;
}

public void setOverAllBookRating(double overAllBookRating) {
	this.overAllBookRating = overAllBookRating;
}

public int getTotalRatings() {
	return totalRatings;
}

public void setTotalRatings(int totalRatings) {
	this.totalRatings = totalRatings;
}

public List<BookRatingDocument> getBookRatingDocumentList() {
	return bookRatingDocumentList;
}

public void setBookRatingDocumentList(List<BookRatingDocument> bookRatingDocumentList) {
	this.bookRatingDocumentList = bookRatingDocumentList;
}
}
