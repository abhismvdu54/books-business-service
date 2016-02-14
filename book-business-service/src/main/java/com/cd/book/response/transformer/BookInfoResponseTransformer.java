package com.cd.book.response.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cd.book.dto.BookInfo;
import com.cd.book.dto.BookInfoDetailsDTO;
import com.cd.book.response.BooksInfoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class BookInfoResponseTransformer {

	@SuppressWarnings("unchecked")
	public BooksInfoResponse transformIntoBookResponse(JSONObject booksInfo, List<String> olids){
		BooksInfoResponse response = new BooksInfoResponse();
		List<BookInfo> listOfBookInfo = new ArrayList<BookInfo>();
		//loop through all the olids and make the list of book info
		for(String olid : olids){
			String key = "OLID:"+olid;
			Map<String, Object> map = (Map<String, Object>) booksInfo.get(key);
			BookInfo bookInfo = populateBookInfoObject(map);
			listOfBookInfo.add(bookInfo);
		}
		response.setListOfBooks(listOfBookInfo);
		setSuccessMessage(response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private BookInfo populateBookInfoObject(Map<String, Object> map){
		BookInfo bookInfo = new BookInfo();
		
		Map<String, Object> detailsMap = (Map<String, Object>)map.get("details");
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		BookInfoDetailsDTO bookInfoDetailsDTO = gson.fromJson(detailsMap.toString(), BookInfoDetailsDTO.class);
		if(bookInfoDetailsDTO.getCovers() !=null && bookInfoDetailsDTO.getCovers().length >0){
		bookInfo.setCover_i(bookInfoDetailsDTO.getCovers()[0]);
		}
		if(map.get("preview_url") != null){
		bookInfo.setIaUrl((String)map.get("preview_url"));
		}
		if(map.get("preview") != null){
			bookInfo.setPreview((String)map.get("preview"));
			}
		bookInfo.setPublisher(bookInfoDetailsDTO.getPublishers());
		bookInfo.setPublish_place(bookInfoDetailsDTO.getPublish_places());
		String[] publishDate = new String[1];
		publishDate[0] = bookInfoDetailsDTO.getPublish_date();
		bookInfo.setPublish_date(publishDate);
		bookInfo.setTitle(bookInfoDetailsDTO.getTitle());
		
		//set the authors
		if(bookInfoDetailsDTO.getAuthors() != null){
		String[] authors = new String[bookInfoDetailsDTO.getAuthors().size()];
		for(int i = 0;  i< bookInfoDetailsDTO.getAuthors().size(); i++){
			authors[i] = bookInfoDetailsDTO.getAuthors().get(i).getName();
		}
		bookInfo.setAuthor_name(authors);
		if(bookInfoDetailsDTO.getPreview() !=null){
			System.out.println(bookInfoDetailsDTO.getPreview()+"..................");
		}
		
		}
		return bookInfo;
	}
	 public void setSuccessMessage(BooksInfoResponse response){
		 response.setCode(HttpStatus.OK.value());
	     response.setStatus(HttpStatus.OK.value());
	     response.setMessage("Success");
	 }
}
