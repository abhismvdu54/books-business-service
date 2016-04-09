package com.cd.book.response;

import java.util.List;

import com.cd.book.dto.BookInfo;

public class BooksInfoResponse extends BaseResponse{

	private List<BookInfo> listOfBooks;

	public List<BookInfo> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<BookInfo> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	
}
