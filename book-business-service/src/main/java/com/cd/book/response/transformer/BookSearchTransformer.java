package com.cd.book.response.transformer;

import java.util.HashMap;
import java.util.Map;

import com.cd.book.dto.BookInfo;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.response.SearchedBookBSResponse;

public class BookSearchTransformer {

 public SearchedBookBSResponse transformIntoReadableNBorrowable(BookSearchDTO books){
	 SearchedBookBSResponse response = new SearchedBookBSResponse();
	 
	 //divide the books into 2 parts called readable and borrowable
	 Map<String, BookSearchDTO> mapForDiffBooks =  divideBooksIntoParts(books);
	 response.setReadableBooks(mapForDiffBooks.get("readableBooks"));
	 response.setBorrowableBooks(mapForDiffBooks.get("borrowableBooks"));
	 response.setStart(books.getStart());
	 response.setBookNumFound(books.getNum_found());
	 return response;
 }
 private Map<String, BookSearchDTO> divideBooksIntoParts(BookSearchDTO books){
	 Map<String, BookSearchDTO> mapForDiffBooks = new HashMap<String, BookSearchDTO>();
	 BookSearchDTO readableBooks = new BookSearchDTO();
	 BookSearchDTO borrowableBooks = new BookSearchDTO();
	 for(BookInfo bookInfo : books.getDocs()){
		 System.out.println(bookInfo.getPreview());
		 if("full".equalsIgnoreCase(bookInfo.getPreview())){
			 readableBooks.getDocs().add(bookInfo);
		 }else if("borrow".equalsIgnoreCase(bookInfo.getPreview())){
			 borrowableBooks.getDocs().add(bookInfo);
		 }
	 }
	 mapForDiffBooks.put("readableBooks", readableBooks);
	 mapForDiffBooks.put("borrowableBooks", borrowableBooks);
	 return mapForDiffBooks;
 }
}
