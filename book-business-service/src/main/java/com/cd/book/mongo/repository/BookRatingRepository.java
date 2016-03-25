package com.cd.book.mongo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cd.book.mongo.document.BookRatingDocument;

public interface BookRatingRepository extends MongoRepository<BookRatingDocument, Serializable>{
	
	List<BookRatingDocument> findByBookId(String bookId);

	@Query("{bookId:{'$in':?0}}")
	List<BookRatingDocument> findByBookIds(List<String> bookIds);
}
