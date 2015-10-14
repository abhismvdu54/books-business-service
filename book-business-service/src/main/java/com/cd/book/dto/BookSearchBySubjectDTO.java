package com.cd.book.dto;

import java.util.List;

public class BookSearchBySubjectDTO {

	private List<SearchedBookInfoBySubject> works;
	private int work_count;
	
	public List<SearchedBookInfoBySubject> getWorks() {
		return works;
	}
	public void setWorks(List<SearchedBookInfoBySubject> works) {
		this.works = works;
	}
	public int getWork_count() {
		return work_count;
	}
	public void setWork_count(int work_count) {
		this.work_count = work_count;
	}
}
