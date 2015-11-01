package com.cd.book.response.transformer;

import static com.cd.book.business.constant.CommonConstants.ia_END_POINT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cd.book.dto.BookInfo;
import com.cd.book.dto.BookSearchDTO;
import com.cd.book.response.SearchedBookBSResponse;

@Component
public class BookSearchTransformer {

 public SearchedBookBSResponse transformIntoReadableNBorrowable(BookSearchDTO books){
	 
	 //loop through books and find out the different conditions to divide into borrowable and readable
	 List<BookInfo> readableBooks = new ArrayList<BookInfo>();
	 List<BookInfo> borrowableBooks = new ArrayList<BookInfo>();
	 for(BookInfo bookInfo : books.getDocs()){
		 //check for the condition for the readable books
		 if(bookInfo.getEbook_count_i() > 0 && bookInfo.getIa().length > 0 && bookInfo.getLending_identifier_s() == null){
			 //loop through the no of ebook counts add it to the readable links
			 BookInfo readableBookInfo = bookInfo;
			 List<BookInfo> editionBooksList = new ArrayList<BookInfo>();
			 for(int i = 0; i< bookInfo.getIa().length; i++){
				 //add the latest edition of the book as primary
				if( i== 0 ){
				 String iaStr = bookInfo.getIa()[i];
				 readableBookInfo.setIaStr(iaStr);
				 readableBookInfo.setIaUrl(ia_END_POINT+iaStr);
				 readableBooks.add(readableBookInfo);
				}
				//edition books for the title  with readable links
				
				 if(i > 0){
					 //if there are more books with internet archieve links (more editions) then add it as editions with the latest one
					 BookInfo editionBookInfo = new BookInfo();
					 editionBookInfo.setIaStr(bookInfo.getIa()[i]);
					 editionBookInfo.setIaUrl(ia_END_POINT + bookInfo.getIa()[i]);
					 editionBooksList.add(editionBookInfo);
					 }
			 }
			 readableBookInfo.setEditions(editionBooksList);
		 }
		 
		 //check for the condition for the borrowable books
		 else if(bookInfo.getEbook_count_i() > 0 && bookInfo.getIa().length > 0 && bookInfo.getLending_identifier_s() != null){
			 //loop through the no of ebook counts add it to the readable links
			 BookInfo borrowableBookInfo = bookInfo;
			 List<BookInfo> editionBooksList = new ArrayList<BookInfo>();
			 for(int i = 0; i< bookInfo.getIa().length; i++){
				 if(i == 0){
				 String iaStr = bookInfo.getIa()[i];
				 borrowableBookInfo.setIaStr(iaStr);
				 borrowableBookInfo.setIaUrl(ia_END_POINT+iaStr);
				 borrowableBooks.add(borrowableBookInfo);
				 }else if(i > 0){
					 //if there are more books with internet archieve links (more editions) then add it as editions with the latest one
					 BookInfo editionBookInfo = new BookInfo();
					 editionBookInfo.setIaStr(bookInfo.getIa()[i]);
					 editionBookInfo.setIaUrl(ia_END_POINT + bookInfo.getIa()[i]);
					 editionBooksList.add(editionBookInfo);
				 }
			 }
			 borrowableBookInfo.setEditions(editionBooksList);
		 }
	 }
	 
	 
	 SearchedBookBSResponse response = new SearchedBookBSResponse();
	 response.setReadableBooks(readableBooks);
	 response.setBorrowableBooks(borrowableBooks);
	 response.setStart(books.getStart());
	 response.setBookNumFound(books.getNum_found());
	 return response;
 }
/* private Map<String, BookSearchDTO> divideBooksIntoParts(BookSearchDTO books){
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
 }*/
 
 public void setSuccessMessage(SearchedBookBSResponse response){
	 response.setCode(HttpStatus.OK.value());
     response.setStatus(HttpStatus.OK.value());
     response.setMessage("Success");
 }
}
