package com.cd.book.business.domain.request.bookShelf;

import javax.validation.constraints.NotNull;

public class BookShelfUpdateRequest{

	@NotNull(message ="Book shelf id can't be null")
	private Integer bookShelfId;

	@NotNull(message = "BookShelf name can't be null")
	private String bookShelfName;
	
	@NotNull(message = "User email can't be null")
	private String userEmail;

	public int getBookShelfId() {
		return bookShelfId;
	}

	public void setBookShelfId(Integer bookShelfId) {
		this.bookShelfId = bookShelfId;
	}
	public String getBookShelfName() {
		return bookShelfName;
	}
	public void setBookShelfName(String bookShelfName) {
		this.bookShelfName = bookShelfName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
