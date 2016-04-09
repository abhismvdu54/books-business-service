package com.cd.book.mongo.domain;

import java.io.File;
import java.util.Calendar;
import java.util.List;

public class Notes {
	private String collegeId;
	private String collegeName;
	private String branchFullname;
	private String branchShortname;
	private String semester;
	private String subect;
    private String topic;
    private List<User> likedBy;
	private Integer likes;
	private List<UserComment> userComments;
	private String fileLocation;
	private transient List<File> files;
	private Calendar date;
	
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSubect() {
		return subect;
	}
	public void setSubect(String subect) {
		this.subect = subect;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<User> getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public List<UserComment> getUserComments() {
		return userComments;
	}
	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getBranchFullname() {
		return branchFullname;
	}
	public void setBranchFullname(String branchFullname) {
		this.branchFullname = branchFullname;
	}
	public String getBranchShortname() {
		return branchShortname;
	}
	public void setBranchShortname(String branchShortname) {
		this.branchShortname = branchShortname;
	}
}
