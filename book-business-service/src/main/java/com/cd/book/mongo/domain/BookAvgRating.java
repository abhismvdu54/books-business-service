package com.cd.book.mongo.domain;

public class BookAvgRating {

	private String _id;
	private double avgRating;
	private int noOfRatings;
	private int sumOfRatings;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public int getNoOfRatings() {
		return noOfRatings;
	}
	public void setNoOfRatings(int noOfRatings) {
		this.noOfRatings = noOfRatings;
	}
	public int getSumOfRatings() {
		return sumOfRatings;
	}
	public void setSumOfRatings(int sumOfRatings) {
		this.sumOfRatings = sumOfRatings;
	}
}
