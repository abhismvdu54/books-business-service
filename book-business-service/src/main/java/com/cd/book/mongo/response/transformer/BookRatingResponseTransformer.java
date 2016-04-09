package com.cd.book.mongo.response.transformer;

import org.springframework.stereotype.Component;

import com.cd.book.mongo.response.BookAvgRatingResponse;
import com.cd.book.mongo.response.BookRatingResponse;
import com.cd.book.response.transformer.BaseResponseTransfomer;

@Component
public class BookRatingResponseTransformer extends BaseResponseTransfomer{

	public BookRatingResponse transformIntoSuccessResponse(BookRatingResponse response){
		super.buildSuccessMessage(response);
		return response;
	}

	public BookAvgRatingResponse transformAvgRatingIntoSuccessResponse(BookAvgRatingResponse response){
		super.buildSuccessMessage(response);
		return response;
	}
	
	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
