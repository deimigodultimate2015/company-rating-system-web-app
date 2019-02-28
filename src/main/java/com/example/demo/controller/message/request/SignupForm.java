package com.example.demo.controller.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupForm {
	
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must containt at least 3 characters")
	private String fullname;
	
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50)
	private String username;
	
	@NotBlank(message = "Email is required")
	@Email
	@Size(max = 256)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 40, message = "password must containt at least 6 characters")
	private String password;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
