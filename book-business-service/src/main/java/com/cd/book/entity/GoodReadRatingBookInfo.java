package com.cd.book.entity;

public class GoodReadRatingBookInfo {
	private int id;
	private String isbn;
	private String isbn13;
	private int ratings_count;
	private int reviews_count;
	private int text_reviews_count;
	private int work_ratings_count;
	private int work_text_reviews_count;
	private int work_reviews_count;
	private double average_rating;

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public int getRatings_count() {
		return ratings_count;
	}
	public void setRatings_count(int ratings_count) {
		this.ratings_count = ratings_count;
	}
	public int getReviews_count() {
		return reviews_count;
	}
	public void setReviews_count(int reviews_count) {
		this.reviews_count = reviews_count;
	}
	public int getText_reviews_count() {
		return text_reviews_count;
	}
	public void setText_reviews_count(int text_reviews_count) {
		this.text_reviews_count = text_reviews_count;
	}
	public int getWork_ratings_count() {
		return work_ratings_count;
	}
	public void setWork_ratings_count(int work_ratings_count) {
		this.work_ratings_count = work_ratings_count;
	}
	public int getWork_text_reviews_count() {
		return work_text_reviews_count;
	}
	public void setWork_text_reviews_count(int work_text_reviews_count) {
		this.work_text_reviews_count = work_text_reviews_count;
	}
	public double getAverage_rating() {
		return average_rating;
	}
	public void setAverage_rating(double average_rating) {
		this.average_rating = average_rating;
	}
	public int getWork_reviews_count() {
		return work_reviews_count;
	}
	public void setWork_reviews_count(int work_reviews_count) {
		this.work_reviews_count = work_reviews_count;
	}

}
