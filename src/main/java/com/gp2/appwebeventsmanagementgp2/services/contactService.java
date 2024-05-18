package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.contact;

public interface contactService {
	List<contact> getAllcontacts();
    contact getContactById(Long id);
    contact saveContact(contact contact);
    contact updateContact(Long id, contact updatedContact);
    void deleteContact(Long id);
}
