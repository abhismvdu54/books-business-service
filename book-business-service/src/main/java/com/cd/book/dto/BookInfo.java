package com.cd.book.dto;

public class BookInfo {
	private String title;
	private Boolean has_fulltext;
	private String cover_i;
	private Integer ebook_count_i;
	private Integer edition_count;
	private Integer first_publish_year;
	
	
	private String edition_key[];
	private String author_name[];
	private String isbn[];
	private String oclc[];
	private String lccn[];
	private String publisher[];
	private String publish_date[];
	private String publish_year[];
	private String thumbnailUrl;
	private String preview;
	private String infoUrl;
	private String previewUrl;
	
	private GoodReadReviewRating goodReadReviewRating;  
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getHas_fulltext() {
		return has_fulltext;
	}
	public void setHas_fulltext(Boolean has_fulltext) {
		this.has_fulltext = has_fulltext;
	}
	public String getCover_i() {
		return cover_i;
	}
	public void setCover_i(String cover_i) {
		this.cover_i = cover_i;
	}
	public Integer getEbook_count_i() {
		return ebook_count_i;
	}
	public void setEbook_count_i(Integer ebook_count_i) {
		this.ebook_count_i = ebook_count_i;
	}
	public Integer getEdition_count() {
		return edition_count;
	}
	public void setEdition_count(Integer edition_count) {
		this.edition_count = edition_count;
	}
	public Integer getFirst_publish_year() {
		return first_publish_year;
	}
	public void setFirst_publish_year(Integer first_publish_year) {
		this.first_publish_year = first_publish_year;
	}
	public String[] getEdition_key() {
		return edition_key;
	}
	public void setEdition_key(String[] edition_key) {
		this.edition_key = edition_key;
	}
	public String[] getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String[] author_name) {
		this.author_name = author_name;
	}
	public String[] getIsbn() {
		return isbn;
	}
	public void setIsbn(String[] isbn) {
		this.isbn = isbn;
	}
	public String[] getOclc() {
		return oclc;
	}
	public void setOclc(String[] oclc) {
		this.oclc = oclc;
	}
	public String[] getPublisher() {
		return publisher;
	}
	public void setPublisher(String[] publisher) {
		this.publisher = publisher;
	}
	public String[] getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String[] publish_date) {
		this.publish_date = publish_date;
	}
	public String[] getPublish_year() {
		return publish_year;
	}
	public void setPublish_year(String[] publish_year) {
		this.publish_year = publish_year;
	}
	public String[] getLccn() {
		return lccn;
	}
	public void setLccn(String[] lccn) {
		this.lccn = lccn;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	public GoodReadReviewRating getGoodReadReviewRating() {
		return goodReadReviewRating;
	}
	public void setGoodReadReviewRating(GoodReadReviewRating goodReadReviewRating) {
		this.goodReadReviewRating = goodReadReviewRating;
	}
}
