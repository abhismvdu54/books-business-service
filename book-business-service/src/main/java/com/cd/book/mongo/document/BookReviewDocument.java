package com.cd.book.mongo.document;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cd.book.mongo.domain.User;
import com.cd.book.mongo.domain.UserComment;

@Document(collection="book_review")
public class BookReviewDocument {

	@Id
	private String id;
	
	@NotNull(message="Book id can't be null")
	private String bookId;
	
	@NotNull(message = "User can't be null")
	private User user;
	
	@NotNull(message = "review can't be null")
	private String review;
	
	private List<User> likedBy;
	
	@NotNull(message = "date can't be null")
	private Calendar date;
	
	private Integer likes;
	
	@NotNull(message = "User Comment can't be null")
	private List<UserComment> userComments;
	
	private transient BookRatingDocument bookRatingDocument;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public BookRatingDocument getBookRatingDocument() {
		return bookRatingDocument;
	}
	public void setBookRatingDocument(BookRatingDocument bookRatingDocument) {
		this.bookRatingDocument = bookRatingDocument;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public List<User> getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public List<UserComment> getUserComments() {
		return userComments;
	}
	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
}
