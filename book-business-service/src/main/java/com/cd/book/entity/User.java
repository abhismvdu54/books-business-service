package com.cd.book.entity;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer userId;

	public Integer getUserId() {
        return this.userId;
    }

	public void setUserId(Integer id) {
        this.userId = id;
    }

	@OneToMany(mappedBy = "userId")
    private Set<Faculty> faculties;

	@OneToMany(mappedBy = "userId")
    private Set<Student> students;

	@ManyToOne
    @JoinColumn(name = "USER_TYPE_ID", referencedColumnName = "USER_TYPE_ID")
    private UserType userTypeId;

	@Column(name = "EMAIL_ID", length = 100, unique = true)
    private String emailId;

	@Column(name = "FIRST_NAME", length = 100)
    private String firstName;

	@Column(name = "LAST_NAME", length = 50)
    private String lastName;

	@Column(name = "MIDDLE_NAME", length = 50)
    private String middleName;

	@Column(name = "PWD", length = 25)
    private String pwd;

	@Column(name = "PHONE_NUMBER", length = 15)
    private String phoneNumber;

	@Column(name = "COUNTRY_ID")
    private Integer countryId;

	@Column(name = "ADDRESS", length = 200)
    private String address;

	@Column(name = "ABOUT_ME", length = 200)
    private String aboutMe;

	@Column(name = "COLLEGE_ID")
    private Integer collegeId;

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

	public Set<Faculty> getFaculties() {
        return faculties;
    }

	public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

	public Set<Student> getStudents() {
        return students;
    }

	public void setStudents(Set<Student> students) {
        this.students = students;
    }

	public UserType getUserTypeId() {
        return userTypeId;
    }

	public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

	public String getEmailId() {
        return emailId;
    }

	public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

	public String getFirstName() {
        return firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getMiddleName() {
        return middleName;
    }

	public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

	public String getPwd() {
        return pwd;
    }

	public void setPwd(String pwd) {
        this.pwd = pwd;
    }

	public String getPhoneNumber() {
        return phoneNumber;
    }

	public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	public Integer getCountryId() {
        return countryId;
    }

	public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

	public String getAddress() {
        return address;
    }

	public void setAddress(String address) {
        this.address = address;
    }

	public String getAboutMe() {
        return aboutMe;
    }

	public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

	public Integer getCollegeId() {
        return collegeId;
    }

	public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
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
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("faculties", "students", "userTypeId").toString();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pwd;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
