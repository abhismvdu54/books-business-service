package com.cd.book.mongo.response;

import java.util.List;

import com.cd.book.mongo.domain.BookAvgRating;
import com.cd.book.response.CommonBaseResponse;

public class BookAvgRatingResponse  extends CommonBaseResponse{
	
List<BookAvgRating> bookAvgRating;

public List<BookAvgRating> getBookAvgRating() {
	return bookAvgRating;
}

public void setBookAvgRating(List<BookAvgRating> bookAvgRating) {
	this.bookAvgRating = bookAvgRating;
}
}
