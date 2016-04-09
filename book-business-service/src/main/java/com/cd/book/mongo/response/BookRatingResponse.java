package com.cd.book.mongo.response;

import java.util.List;

import com.cd.book.mongo.document.BookRatingDocument;
import com.cd.book.response.BaseResponse;

public class BookRatingResponse extends BaseResponse{

	private BookRatingDocument bookRatingDocument;
	private List<BookRatingDocument> bookRatingDocumentList;
	private double overAllBookRating;
	
	public BookRatingDocument getBookRatingDocument() {
		return bookRatingDocument;
	}
	public void setBookRatingDocument(BookRatingDocument bookRatingDocument) {
		this.bookRatingDocument = bookRatingDocument;
	}
	public List<BookRatingDocument> getBookRatingDocumentList() {
		return bookRatingDocumentList;
	}
	public void setBookRatingDocumentList(List<BookRatingDocument> bookRatingDocumentList) {
		this.bookRatingDocumentList = bookRatingDocumentList;
	}
	public double getOverAllBookRating() {
		return overAllBookRating;
	}
	public void setOverAllBookRating(double overAllBookRating) {
		this.overAllBookRating = overAllBookRating;
	}
	
}
