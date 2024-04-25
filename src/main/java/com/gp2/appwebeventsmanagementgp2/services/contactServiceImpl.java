package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.repositories.contactRepository;

@Service
public class contactServiceImpl implements contactService {

	private contactRepository contactRepository ;

	public contactServiceImpl(contactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public List<contact> getAllcontacts() {
		return contactRepository.findAll();
	}

	@Override
	public contact saveContact(contact contact) {
		return contactRepository.save(contact) ;
	}

	@Override
	public contact getContactById(Long contact_id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(contact_id).orElseThrow();
	}

	@Override
	public contact updateContact(Long contact_id, contact updatedContact) {
        contact existingContact = getContactById(contact_id);
        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        return contactRepository.save(existingContact);

	}

	@Override
	public void deleteContact(Long contact_id) {
		contactRepository.deleteById(contact_id);

	}

}
