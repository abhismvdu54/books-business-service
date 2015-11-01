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
@Table(name = "book_review")
public class BookReview {

	@ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", nullable = false)
    private Book bookId;

	@ManyToOne
    @JoinColumn(name = "BOOK_RATING_ID", referencedColumnName = "BOOK_RATING_INFO_ID", nullable = false)
    private BookRatingInfo bookRatingId;

	@Column(name = "USER_ID")
    @NotNull
    private Integer userId;

	@Column(name = "LIKED")
    private Boolean liked;

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

	public BookRatingInfo getBookRatingId() {
        return bookRatingId;
    }

	public void setBookRatingId(BookRatingInfo bookRatingId) {
        this.bookRatingId = bookRatingId;
    }

	public Integer getUserId() {
        return userId;
    }

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public Boolean getLiked() {
        return liked;
    }

	public void setLiked(Boolean liked) {
        this.liked = liked;
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
    @Column(name = "BOOK_REVIEW_ID")
    private Integer bookReviewId;

	public Integer getBookReviewId() {
        return this.bookReviewId;
    }

	public void setBookReviewId(Integer id) {
        this.bookReviewId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("bookId", "bookRatingId").toString();
    }
}
