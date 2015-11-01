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
@Table(name = "book_shelf")
public class BookShelf {

	@OneToMany(mappedBy = "bookShelfId")
    private Set<BooksInBookShelf> booksInBookShelves;

	@Column(name = "USER_ID", unique = true)
    @NotNull
    private Integer userId;

	@Column(name = "SHELF_NAME", length = 30, unique = true)
    private String shelfName;

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

	public Set<BooksInBookShelf> getBooksInBookShelves() {
        return booksInBookShelves;
    }

	public void setBooksInBookShelves(Set<BooksInBookShelf> booksInBookShelves) {
        this.booksInBookShelves = booksInBookShelves;
    }

	public Integer getUserId() {
        return userId;
    }

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public String getShelfName() {
        return shelfName;
    }

	public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

	public Boolean isActive() {
        return isActive;
    }

	public void setActive(Boolean isActive) {
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

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("booksInBookShelves").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_SHELF_ID")
    private Integer bookShelfId;

	public Integer getBookShelfId() {
        return this.bookShelfId;
    }

	public void setBookShelfId(Integer id) {
        this.bookShelfId = id;
    }
}
