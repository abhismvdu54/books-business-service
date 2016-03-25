package com.cd.book.mongo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cd.book.mongo.document.BookReviewDocument;

public interface BookReviewRepository extends MongoRepository<BookReviewDocument, Serializable>{

	List<BookReviewDocument> findByBookId(String bookId);
}
