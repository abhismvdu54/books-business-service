package com.cd.book.mongo.document;

import javax.validation.constraints.NotNull;

public class UserRating {

	@NotNull(message="User can't be null")
	private User user;
	
	@NotNull(message="Book rating can't be null")
	private int rating;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
