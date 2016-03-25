package com.cd.book.mongo.document;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UserReview {

	@NotNull(message="uer can't be null")
	private User user;
	private String rating;
	private List<UserComment> comments;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public List<UserComment> getComments() {
		return comments;
	}
	public void setComments(List<UserComment> comments) {
		this.comments = comments;
	}
}
