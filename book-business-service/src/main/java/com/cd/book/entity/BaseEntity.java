
package com.cd.book.entity;

/**
 * @author Mayank
 * 
 */
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.cd.book.constant.StatusType;

@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    private Date createdDate;

    private User createdBy;

    private Date lastModifiedDate;

    private User lastModifiedBy;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    public Date getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
	return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
	this.lastModifiedDate = lastModifiedDate;
    }

    public User getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(User createdBy) {
	this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
	return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
	this.lastModifiedBy = lastModifiedBy;
    }

    public StatusType getStatus() {
	return status;
    }

    public void setStatus(StatusType status) {
	this.status = status;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @PreUpdate
    public void preUpdate() {
	lastModifiedDate = new Date();
    }

    @PrePersist
    public void prePersist() {
	Date currentDate = new Date();
	createdDate = currentDate;
	lastModifiedDate = currentDate;
    }
}
