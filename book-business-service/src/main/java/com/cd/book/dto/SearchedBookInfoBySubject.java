package com.cd.book.dto;

public class SearchedBookInfoBySubject {
private int cover_id;
private String title;
private String lending_edition;
private String cover_edition_key;
private boolean checked_out;

public int getCover_id() {
	return cover_id;
}
public void setCover_id(int cover_id) {
	this.cover_id = cover_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getLending_edition() {
	return lending_edition;
}
public void setLending_edition(String lending_edition) {
	this.lending_edition = lending_edition;
}
public String getCover_edition_key() {
	return cover_edition_key;
}
public void setCover_edition_key(String cover_edition_key) {
	this.cover_edition_key = cover_edition_key;
}
public boolean isChecked_out() {
	return checked_out;
}
public void setChecked_out(boolean checked_out) {
	this.checked_out = checked_out;
}
}
