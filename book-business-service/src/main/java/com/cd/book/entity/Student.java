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
@Table(name = "student")
public class Student {

	@ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User userId;

	@Column(name = "BRANCH_ID")
    private Integer branchId;

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

	public User getUserId() {
        return userId;
    }

	public void setUserId(User userId) {
        this.userId = userId;
    }

	public Integer getBranchId() {
        return branchId;
    }

	public void setBranchId(Integer branchId) {
        this.branchId = branchId;
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
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("userId").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Integer studentId;

	public Integer getStudentId() {
        return this.studentId;
    }

	public void setStudentId(Integer id) {
        this.studentId = id;
    }
}
