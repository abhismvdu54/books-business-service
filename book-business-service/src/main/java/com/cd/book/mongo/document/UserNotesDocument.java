package com.cd.book.mongo.document;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cd.book.mongo.domain.NotesRepository;
import com.cd.book.mongo.domain.User;

@Document(collection="user_notes")
public class UserNotesDocument {
	@Id
	private String id;
	@NotNull(message = "User can't be null")
	private User user;
	private List<NotesRepository> notesRepositoryList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<NotesRepository> getNotesRepositoryList() {
		return notesRepositoryList;
	}
	public void setNotesRepositoryList(List<NotesRepository> notesRepositoryList) {
		this.notesRepositoryList = notesRepositoryList;
	}
}
