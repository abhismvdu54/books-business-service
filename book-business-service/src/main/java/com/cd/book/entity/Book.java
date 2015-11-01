package com.cd.book.entity;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "book")
public class Book {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("bookReviews", "booksInBookShelves").toString();
    }

	@OneToMany(mappedBy = "bookId")
    private Set<BookReview> bookReviews;

	@OneToMany(mappedBy = "bookId")
    private Set<BooksInBookShelf> booksInBookShelves;

	@Column(name = "ISBN10", unique = true)
    private Integer isbn10;

	@Column(name = "ISBN13", unique = true)
    private Integer isbn13;

	@Column(name = "TITLE", length = 200, unique = true)
    private String title;

	@Column(name = "AUTHOR", length = 200)
    private String author;

	@Column(name = "PUBLISHER", length = 200)
    private String publisher;

	@Column(name = "CD_RATING", precision = 3, scale = 2)
    private BigDecimal cdRating;

	@Column(name = "SEARCH_COUNT")
    private Integer searchCount;

	@Column(name = "CREATED_ON")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar createdOn;

	@Column(name = "LAST_UPDATED_ON")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar lastUpdatedOn;

	@Column(name = "CREATED_BY", length = 100)
    @NotNull
    private String createdBy;

	@Column(name = "LAST_UPDATED_BY", length = 100)
    @NotNull
    private String lastUpdatedBy;

	public Set<BookReview> getBookReviews() {
        return bookReviews;
    }

	public void setBookReviews(Set<BookReview> bookReviews) {
        this.bookReviews = bookReviews;
    }

	public Set<BooksInBookShelf> getBooksInBookShelves() {
        return booksInBookShelves;
    }

	public void setBooksInBookShelves(Set<BooksInBookShelf> booksInBookShelves) {
        this.booksInBookShelves = booksInBookShelves;
    }

	public Integer getIsbn10() {
        return isbn10;
    }

	public void setIsbn10(Integer isbn10) {
        this.isbn10 = isbn10;
    }

	public Integer getIsbn13() {
        return isbn13;
    }

	public void setIsbn13(Integer isbn13) {
        this.isbn13 = isbn13;
    }

	public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
        this.title = title;
    }

	public String getAuthor() {
        return author;
    }

	public void setAuthor(String author) {
        this.author = author;
    }

	public String getPublisher() {
        return publisher;
    }

	public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

	public BigDecimal getCdRating() {
        return cdRating;
    }

	public void setCdRating(BigDecimal cdRating) {
        this.cdRating = cdRating;
    }

	public Integer getSearchCount() {
        return searchCount;
    }

	public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

	public Calendar getCreatedOn() {
        return createdOn;
    }

	public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

	public Calendar getLastUpdatedOn() {
        return lastUpdatedOn;
    }

	public void setLastUpdatedOn(Calendar lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

	public String getCreatedBy() {
        return createdBy;
    }

	public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

	public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

	public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Integer bookId;

	public Integer getBookId() {
        return this.bookId;
    }

	public void setBookId(Integer id) {
        this.bookId = id;
    }
}
