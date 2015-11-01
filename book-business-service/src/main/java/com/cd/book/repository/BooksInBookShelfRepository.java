package com.cd.book.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cd.book.entity.BooksInBookShelf;

@Repository
public interface BooksInBookShelfRepository extends JpaSpecificationExecutor<BooksInBookShelf>, JpaRepository<BooksInBookShelf, Integer> {
}
