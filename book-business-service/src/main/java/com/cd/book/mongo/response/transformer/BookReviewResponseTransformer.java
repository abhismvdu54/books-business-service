package com.cd.book.mongo.response.transformer;

import org.springframework.stereotype.Component;

import com.cd.book.mongo.response.BookReviewResponse;
import com.cd.book.response.transformer.BaseResponseTransfomer;

@Component
public class BookReviewResponseTransformer extends BaseResponseTransfomer{

	public BookReviewResponse transformIntoSuccessResponse(BookReviewResponse response){
		super.buildSuccessMessage(response);
		return response;
	}

	@Override
	protected int getApplicationCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
