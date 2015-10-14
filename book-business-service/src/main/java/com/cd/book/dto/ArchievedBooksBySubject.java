package com.cd.book.dto;

public class ArchievedBooksBySubject {
private String bib_key;
private String preview;
private String thumbnail_url;
private String preview_url;
private String info_url;

public String getBib_key() {
	return bib_key;
}
public void setBib_key(String bib_key) {
	this.bib_key = bib_key;
}
public String getPreview() {
	return preview;
}
public void setPreview(String preview) {
	this.preview = preview;
}
public String getThumbnail_url() {
	return thumbnail_url;
}
public void setThumbnail_url(String thumbnail_url) {
	this.thumbnail_url = thumbnail_url;
}
public String getPreview_url() {
	return preview_url;
}
public void setPreview_url(String preview_url) {
	this.preview_url = preview_url;
}
public String getInfo_url() {
	return info_url;
}
public void setInfo_url(String info_url) {
	this.info_url = info_url;
}
}
