package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.models.contact;

@Service
public interface contactService {
	List<contact> getAllcontacts();
    contact getContactById(Long id);
    contact saveContact(contact contact);
    contact updateContact(Long id, contact updatedContact);
    void deleteContact(Long id);
}
