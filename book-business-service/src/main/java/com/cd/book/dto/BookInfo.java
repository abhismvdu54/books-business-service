package com.cd.book.dto;

import java.util.List;

public class BookInfo {
	private String title;
	private Boolean has_fulltext;
	private String cover_i;
	private Integer ebook_count_i;
	private Integer edition_count;
	private Integer first_publish_year;
	
	private String ia[];
	private String iaStr;
	private String iaUrl;
	private String id_goodreads[];
	private String language[];
	private String edition_key[];
	private String author_name[];
	private String isbn[];
	private String oclc[];
	private String lccn[];
	private String publisher[];
	private String publish_date[];
	private String publish_year[];
	private String publish_place[];
	private String lending_edition_s;
	private String lending_identifier_s;
	private String thumbnailUrl;
	private String preview;
	private String infoUrl;
	private String previewUrl;
	private boolean public_scan_b;
	private String ia_collection_s;
	private double bookRating;
	private double noOfRatings;
	
	private List<BookInfo> editions;
	
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
	@Override
	public String toString(){
		return "title:"+title+
				"has_fulltext:"+has_fulltext+
				"cover_i:"+cover_i;
	}
	public String[] getIa() {
		return ia;
	}
	public void setIa(String[] ia) {
		this.ia = ia;
	}
	public String[] getId_goodreads() {
		return id_goodreads;
	}
	public void setId_goodreads(String[] id_goodreads) {
		this.id_goodreads = id_goodreads;
	}
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
	}
	public String[] getPublish_place() {
		return publish_place;
	}
	public void setPublish_place(String[] publish_place) {
		this.publish_place = publish_place;
	}
	public String getLending_edition_s() {
		return lending_edition_s;
	}
	public void setLending_edition_s(String lending_edition_s) {
		this.lending_edition_s = lending_edition_s;
	}
	public String getLending_identifier_s() {
		return lending_identifier_s;
	}
	public void setLending_identifier_s(String lending_identifier_s) {
		this.lending_identifier_s = lending_identifier_s;
	}
	public String getIaStr() {
		return iaStr;
	}
	public void setIaStr(String iaStr) {
		this.iaStr = iaStr;
	}
	public String getIaUrl() {
		return iaUrl;
	}
	public void setIaUrl(String iaUrl) {
		this.iaUrl = iaUrl;
	}
	public List<BookInfo> getEditions() {
		return editions;
	}
	public void setEditions(List<BookInfo> editions) {
		this.editions = editions;
	}
	public boolean isPublic_scan_b() {
		return public_scan_b;
	}
	public void setPublic_scan_b(boolean public_scan_b) {
		this.public_scan_b = public_scan_b;
	}
	public String getIa_collection_s() {
		return ia_collection_s;
	}
	public void setIa_collection_s(String ia_collection_s) {
		this.ia_collection_s = ia_collection_s;
	}
	public double getBookRating() {
		return bookRating;
	}
	public void setBookRating(double bookRating) {
		this.bookRating = bookRating;
	}
	public double getNoOfRatings() {
		return noOfRatings;
	}
	public void setNoOfRatings(double noOfRatings) {
		this.noOfRatings = noOfRatings;
	}
}
