package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "contacts")
public class contact {
    @Id
    @GeneratedValue
    private Long contactId;

    private String name;
    public contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String email;
    private String country;
    public int phoneNumber ;
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public contact(Long contactId, String name, String email) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



}
