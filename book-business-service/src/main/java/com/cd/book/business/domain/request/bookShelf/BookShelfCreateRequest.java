package com.cd.book.business.domain.request.bookShelf;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@ComponentScan
@JsonInclude(Include.NON_NULL)
public class BookShelfCreateRequest {
	
@NotNull(message = "User id can't be null")
private int userId;

@NotNull(message = "BookShelf name can't be null")
private String bookShelfName;

@NotNull(message = "User email can't be null")
private String userEmail;

public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
