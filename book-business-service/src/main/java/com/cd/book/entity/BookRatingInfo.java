package com.cd.book.entity;
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
@Table(name = "book_rating_info")
public class BookRatingInfo {

    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("bookReviews").toString();
    }

    @OneToMany(mappedBy = "bookRatingId")
    private Set<BookReview> bookReviews;

    @Column(name = "BOOK_RATING_NO", unique = true)
    @NotNull
    private Integer bookRatingNo;

    @Column(name = "BOOK_RATING_LABEL", length = 30)
    private String bookRatingLabel;

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

    public Integer getBookRatingNo() {
        return bookRatingNo;
    }

    public void setBookRatingNo(Integer bookRatingNo) {
        this.bookRatingNo = bookRatingNo;
    }

    public String getBookRatingLabel() {
        return bookRatingLabel;
    }

    public void setBookRatingLabel(String bookRatingLabel) {
        this.bookRatingLabel = bookRatingLabel;
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
    @Column(name = "BOOK_RATING_INFO_ID")
    private Integer bookRatingInfoId;

    public Integer getBookRatingInfoId() {
        return this.bookRatingInfoId;
    }

    public void setBookRatingInfoId(Integer id) {
        this.bookRatingInfoId = id;
    }
}
