package com.cd.book.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cd.book.entity.BookRatingInfo;

@Repository
public interface BookRatingInfoRepository extends JpaSpecificationExecutor<BookRatingInfo>, JpaRepository<BookRatingInfo, Integer> {
}
