package com.cd.book.entity;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books_in_book_shelf")
public class BooksInBookShelf {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("bookId", "bookShelfId").toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOKS_IN_BOOK_SHELF_ID")
    private Integer booksInBookShelfId;

    public Integer getBooksInBookShelfId() {
        return this.booksInBookShelfId;
    }

    public void setBooksInBookShelfId(Integer id) {
        this.booksInBookShelfId = id;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", nullable = false)
    private Book bookId;

    @ManyToOne
    @JoinColumn(name = "BOOK_SHELF_ID", referencedColumnName = "BOOK_SHELF_ID", nullable = false)
    private BookShelf bookShelfId;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

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

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public BookShelf getBookShelfId() {
        return bookShelfId;
    }

    public void setBookShelfId(BookShelf bookShelfId) {
        this.bookShelfId = bookShelfId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
}
