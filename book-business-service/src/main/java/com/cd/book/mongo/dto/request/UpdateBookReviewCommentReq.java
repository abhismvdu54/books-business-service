package com.cd.book.mongo.dto.request;

import java.util.List;

import com.cd.book.mongo.document.UserComment;

//this request class is for updating the book review comments
public class UpdateBookReviewCommentReq {

	private String bookReviewId;
	private UserComment userComment;
	private List<UserComment> userComments;
	private int userCommentIndex;
	
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
	public List<UserComment> getUserComments() {
		return userComments;
	}
	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
	public int getUserCommentIndex() {
		return userCommentIndex;
	}
	public void setUserCommentIndex(int userCommentIndex) {
		this.userCommentIndex = userCommentIndex;
	}
}
