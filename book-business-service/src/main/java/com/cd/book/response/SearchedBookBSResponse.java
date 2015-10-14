package com.cd.book.response;

import com.cd.book.dto.BookSearchDTO;

public class SearchedBookBSResponse {
private BookSearchDTO readableBooks;
private BookSearchDTO borrowableBooks;
private int start;
private int bookNumFound;

public BookSearchDTO getReadableBooks() {
	return readableBooks;
}
public void setReadableBooks(BookSearchDTO readableBooks) {
	this.readableBooks = readableBooks;
}
public BookSearchDTO getBorrowableBooks() {
	return borrowableBooks;
}
public void setBorrowableBooks(BookSearchDTO borrowableBooks) {
	this.borrowableBooks = borrowableBooks;
}
public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public int getBookNumFound() {
	return bookNumFound;
}
public void setBookNumFound(int bookNumFound) {
	this.bookNumFound = bookNumFound;
}

}
