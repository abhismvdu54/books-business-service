package com.cd.book.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cd.book.dto.BookShelfDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement(name="bookShelfResponse")
@JsonInclude(Include.NON_NULL)
public class BookShelfResponse extends CommonBaseResponse{
	
private BookShelfDTO bookShelf;
private List<BookShelfDTO> listOfBookShelf;

public BookShelfDTO getBookShelf() {
	return bookShelf;
}

@XmlElement
public void setBookShelf(BookShelfDTO bookShelf) {
	this.bookShelf = bookShelf;
}

public List<BookShelfDTO> getListOfBookShelf() {
	return listOfBookShelf;
}

@XmlElement
public void setListOfBookShelf(List<BookShelfDTO> listOfBookShelf) {
	this.listOfBookShelf = listOfBookShelf;
}
}
