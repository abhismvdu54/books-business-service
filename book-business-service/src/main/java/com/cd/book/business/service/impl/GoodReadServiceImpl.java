package com.cd.book.business.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cd.book.business.service.GoodReadService;
import com.cd.book.exception.BookBusinessServiceException;
import com.cd.book.response.GoodReadRatingBook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class GoodReadServiceImpl implements GoodReadService{

	RestTemplate restTemplate;

	/** (non-Javadoc)
	 * @see com.cd.book.business.service.GoodReadService#retrieveBookRating(java.lang.String)
	 */
	@Override
	public GoodReadRatingBook retrieveBookRating(String goodReadQuery) throws BookBusinessServiceException {
		restTemplate = new RestTemplate();
		GoodReadRatingBook goodReadRatingResponse = null;
		try {
			String goodReadReviewResponse = restTemplate.getForObject(goodReadQuery, String.class);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			goodReadRatingResponse = gson.fromJson(goodReadReviewResponse, GoodReadRatingBook.class);
		} catch (Exception e) {

			final String msg = "Exception while retrieving the data from good read library";
			throw new BookBusinessServiceException(
					0, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		}

		return goodReadRatingResponse;
	}

}
