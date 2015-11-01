package com.cd.book.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cd.book.entity.BookReview;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Integer>, JpaSpecificationExecutor<BookReview> {
}
