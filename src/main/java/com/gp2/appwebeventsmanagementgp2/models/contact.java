package com.gp2.appwebeventsmanagementgp2.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

	@NotBlank
	@Column(unique = true, nullable = false)
    private String name;

	@Email
	@Column(unique = true)
	private String email;

	@Column
    private String country;

	@Column
    private int phoneNumber;

	
	public contact(Long contactId, @NotBlank String name, @Email String email) {
		this.contactId = contactId;
		this.name = name;
		this.email = email;
	}

	public contact(@NotBlank String name, @Email String email, String country, int phoneNumber) {
		this.name = name;
		this.email = email;
		this.country = country;
		this.phoneNumber = phoneNumber;
	}
}
