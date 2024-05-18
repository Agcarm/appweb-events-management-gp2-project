package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.persistence.UniqueConstraint;


@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "email")
	@Email(message = "Please enter a valid email address")  // Validation constraint for email
    @NotBlank(message = "Email cannot be blank")
	private String email;

    // @NotBlank(message = "Password cannot be null")
	@Column(name = "password")
	// @Size(min=4, max=50, message="Password must be between 4 and 15 characters")
	private String password;

	@Column(name = "role")
	private String role;

	// @Size(min = 4, max = 15, message="Password must be between 4 and 5 characters")
	// @NotBlank(message = "Fullname cannot be null")
	@Column(name = "fullname")
	private String fullname;

	public User() {
		super();
	}

	public User(String email, String password, String role, String fullname) {

		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", role=" + role + ", fullname=" + fullname + "]";
	}










}
