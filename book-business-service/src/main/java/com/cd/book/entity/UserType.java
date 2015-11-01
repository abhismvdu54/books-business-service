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
@Table(name = "user_type")
public class UserType {

	@OneToMany(mappedBy = "userTypeId")
    private Set<User> users;

	@Column(name = "USER_TYPE", length = 20, unique = true)
    private String userType;

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

	public Set<User> getUsers() {
        return users;
    }

	public void setUsers(Set<User> users) {
        this.users = users;
    }

	public String getUserType() {
        return userType;
    }

	public void setUserType(String userType) {
        this.userType = userType;
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
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("users").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_TYPE_ID")
    private Integer userTypeId;

	public Integer getUserTypeId() {
        return this.userTypeId;
    }

	public void setUserTypeId(Integer id) {
        this.userTypeId = id;
    }
}
