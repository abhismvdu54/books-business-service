package com.cd.book.dto;

public class GoodReadReviewRating {

	private String isbn;
	private int ratingsCount;
	private int reviewsCount;
	private int textReviewsCount;
	private int workRatingsCount;
	private int workReviewsCount;
	private double averageRating;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getRatingsCount() {
		return ratingsCount;
	}
	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}
	public int getReviewsCount() {
		return reviewsCount;
	}
	public void setReviewsCount(int reviewsCount) {
		this.reviewsCount = reviewsCount;
	}
	public int getTextReviewsCount() {
		return textReviewsCount;
	}
	public void setTextReviewsCount(int textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}
	public int getWorkRatingsCount() {
		return workRatingsCount;
	}
	public void setWorkRatingsCount(int workRatingsCount) {
		this.workRatingsCount = workRatingsCount;
	}
	public int getWorkReviewsCount() {
		return workReviewsCount;
	}
	public void setWorkReviewsCount(int workReviewsCount) {
		this.workReviewsCount = workReviewsCount;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
}
