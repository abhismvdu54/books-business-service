package com.cd.book.mongo.domain;

import java.util.Calendar;
import java.util.List;

public class NotesRepository {
	private String notesRepositoryName;
	private String description;
	private List<Notes> notesList;

	private Calendar date;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public List<Notes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<Notes> notesList) {
		this.notesList = notesList;
	}

	public String getNotesRepositoryName() {
		return notesRepositoryName;
	}

	public void setNotesRepositoryName(String notesRepositoryName) {
		this.notesRepositoryName = notesRepositoryName;
	}
}
