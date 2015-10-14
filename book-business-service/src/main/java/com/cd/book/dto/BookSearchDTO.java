package com.cd.book.dto;

import java.util.ArrayList;
import java.util.List;

public class BookSearchDTO {
private int start;
private int num_found;
private List<BookInfo> docs;

public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public int getNum_found() {
	return num_found;
}
public void setNum_found(int num_found) {
	this.num_found = num_found;
}
public List<BookInfo> getDocs() {
	if(this.docs == null){
		docs = new ArrayList<BookInfo>();
	}
	return docs;
}
public void setDocs(List<BookInfo> docs) {
	this.docs = docs;
}
}
