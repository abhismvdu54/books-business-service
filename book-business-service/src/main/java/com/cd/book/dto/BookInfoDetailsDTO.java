package com.cd.book.dto;

import java.util.List;

public class BookInfoDetailsDTO {

	private String publishers[];
	private String title;
	private String covers[];
	private String publish_country;
	private List<AuthorDTO> authors;
	private String publish_date;
	private String[] publish_places;
	private String preview;
	
	public String[] getPublishers() {
		return publishers;
	}
	public void setPublishers(String[] publishers) {
		this.publishers = publishers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getCovers() {
		return covers;
	}
	public void setCovers(String[] covers) {
		this.covers = covers;
	}
	public String getPublish_country() {
		return publish_country;
	}
	public void setPublish_country(String publish_country) {
		this.publish_country = publish_country;
	}
	public List<AuthorDTO> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String[] getPublish_places() {
		return publish_places;
	}
	public void setPublish_places(String[] publish_places) {
		this.publish_places = publish_places;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
}
