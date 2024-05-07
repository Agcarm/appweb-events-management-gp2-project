package com.gp2.appwebeventsmanagementgp2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

	@NotBlank(message = "Email cannot be null")
	private String email;

	// @NotBlank(message = "Password cannot be null")
	// @Size(min = 4, max = 10, message="Password must be between 4 and 10 characters")
	private String password;

	private String role;

	@NotBlank(message = "Fullname cannot be null")
	@Size(min = 2, max = 100, message = "Fullname must be between 2 and 100 characters")
	private String fullname;

	public UserDto(String email, String password, String role, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
