package com.cd.book.business.domain.request;

public class BookSearchCriteriaRequest {
	
	private String subject;
	private String author;
	private String title;
	private int publishYearStartLimit;
	private int publishYearendLimit;
	private String publisher;
	private String place;
	private String isbn;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPublishYearStartLimit() {
		return publishYearStartLimit;
	}
	public void setPublishYearStartLimit(int publishYearStartLimit) {
		this.publishYearStartLimit = publishYearStartLimit;
	}
	public int getPublishYearendLimit() {
		return publishYearendLimit;
	}
	public void setPublishYearendLimit(int publishYearendLimit) {
		this.publishYearendLimit = publishYearendLimit;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
