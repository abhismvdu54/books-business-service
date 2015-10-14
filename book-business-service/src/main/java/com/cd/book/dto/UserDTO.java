package com.cd.book.dto;

/**
 * @author Mayank
 * 
 */  
public class UserDTO extends BaseDTO{

    private String userName;
    private String email;
    private String password;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
