package com.cd.book.mongo.dto.request;

import javax.validation.constraints.NotNull;

import com.cd.book.mongo.domain.UserComment;

public class AddBookReviewCommentReq {

	@NotNull(message="book review id can't be null")
	private String bookReviewId;
	
	@NotNull(message="user comment can't be null")
	private UserComment userComment;

	public String getBookReviewId() {
		return bookReviewId;
	}

	public void setBookReviewId(String bookReviewId) {
		this.bookReviewId = bookReviewId;
	}

	public UserComment getUserComment() {
		return userComment;
	}

	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}
}
