package com.gp2.appwebeventsmanagementgp2.models;

import java.util.ArrayList;
import java.util.List;

public class contactForm {
    private String name;
    private String email;
    private int phoneNumber;
    private List<contactTypeForm> contactTypes = new ArrayList<>();

    // Getters and Setters
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<contactTypeForm> getContactTypes() {
        return contactTypes;
    }

    public void setContactTypes(List<contactTypeForm> contactTypes) {
        this.contactTypes = contactTypes;
    }
}
