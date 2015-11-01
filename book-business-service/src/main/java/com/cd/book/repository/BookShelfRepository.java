package com.cd.book.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cd.book.entity.BookShelf;

@Repository
public interface BookShelfRepository extends JpaSpecificationExecutor<BookShelf>, 
JpaRepository<BookShelf, Integer> {

	BookShelf findByUserIdAndShelfName(Integer userId, String shelfName);
	
	List<BookShelf> findByUserId(Integer userId);
}
