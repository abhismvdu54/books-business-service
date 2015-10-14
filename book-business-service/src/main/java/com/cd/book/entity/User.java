
package com.cd.book.entity;
/**
 * @author Mayank
 * 
 */
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity 
@Table(name="user_info", schema="user")
public class User extends NamedEntity implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Column(name="password")
    @Length(max = 255)
    private String password;

    @Column(name="email")
    @Length(max = 255)
    private String email;

    

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUsername() {
	return super.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
    	return true;
	//return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
    }

    @Override
    public boolean isAccountNonLocked() {
    //	System.out.println(StatusType.ACTIVE.toString().equals(super.getStatus().toString()));
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	return true;
	//return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
    }

    @Override
    public boolean isEnabled() {
    	return true;
	//return StatusType.ACTIVE.toString().equals(super.getStatus().toString());
    }

    @Override
    public String toString() {
	return "User [email=" + email + "]";
    }

}
