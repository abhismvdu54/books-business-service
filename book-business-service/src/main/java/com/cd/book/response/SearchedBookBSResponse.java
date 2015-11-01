package com.cd.book.response;

import java.util.List;

import com.cd.book.dto.BookInfo;

public class SearchedBookBSResponse extends CommonBaseResponse{
private List<BookInfo> readableBooks;
private List<BookInfo> borrowableBooks;
private int start;
private int bookNumFound;

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
public List<BookInfo> getReadableBooks() {
	return readableBooks;
}
public void setReadableBooks(List<BookInfo> readableBooks) {
	this.readableBooks = readableBooks;
}
public List<BookInfo> getBorrowableBooks() {
	return borrowableBooks;
}
public void setBorrowableBooks(List<BookInfo> borrowableBooks) {
	this.borrowableBooks = borrowableBooks;
}

}
