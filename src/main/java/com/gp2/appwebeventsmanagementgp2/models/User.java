package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.UniqueConstraint;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotBlank(message = "Password cannot be null")
	@Column(name = "password", nullable = false)
	private String password;

	@NotBlank
	@Column(name = "role")
	private String role;

	@Size(min = 4, max = 15, message="Fullname must be between 4 and 5 characters")
	@NotBlank(message = "Fullname cannot be null")
	@Column(name = "fullname")
	private String fullname;

	public User(
			@Email(message = "Please enter a valid email address") @NotBlank(message = "Email cannot be blank") String email,
			@NotBlank(message = "Password cannot be null") @Size(min = 4, max = 50, message = "Password must be between 4 and 15 characters") String password,
			@NotBlank String role,
			@Size(min = 4, max = 15, message = "Fullname must be between 4 and 5 characters") @NotBlank(message = "Fullname cannot be null") String fullname) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", role=" + role + ", fullname=" + fullname + "]";
	}
}
