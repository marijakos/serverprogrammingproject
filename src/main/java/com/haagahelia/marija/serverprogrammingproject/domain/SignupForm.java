package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.validation.constraints.Size;

public class SignupForm {
    @Size(min=0, max=50)
    private String firstName = "";
    
    @Size(min=0, max=50)
    private String lastName = "";
    
    @Size(min=5, max=50)
    private String username = "";

    @Size(min=8, max=30)
    private String password = "";

    @Size(min=8, max=30)
    private String passwordCheck = "";

    private String role = "USER";

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
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
