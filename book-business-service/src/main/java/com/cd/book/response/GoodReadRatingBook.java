package com.cd.book.response;

import java.util.List;

import com.cd.book.entity.GoodReadRatingBookInfo;

public class GoodReadRatingBook {

	private List<GoodReadRatingBookInfo> books;

	public List<GoodReadRatingBookInfo> getBooks() {
		return books;
	}

	public void setBooks(List<GoodReadRatingBookInfo> books) {
		this.books = books;
	}
}
