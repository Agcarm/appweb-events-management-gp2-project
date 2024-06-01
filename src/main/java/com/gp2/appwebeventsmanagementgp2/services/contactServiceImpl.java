package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.contactRepository;

@Service
public class contactServiceImpl implements contactService {

	@Autowired
	private contactRepository contactRepository ;
	@Autowired
	private TaskService tservice;

	@Override
	public List<contact> getAllcontacts() {
		return contactRepository.findAll();
	}

	@Override
	@Transactional
	public contact saveContact(contact contact) {
		return contactRepository.save(contact) ;
	}

	@Override
	public contact getContactById(Long contact_id) {
		return contactRepository.findById(contact_id).orElse(null);
	}

	@Override
	@Transactional
	public contact updateContact(Long contact_id, contact updatedContact) {
        contact existingContact = getContactById(contact_id);
        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
        existingContact.setCountry(updatedContact.getCountry());
        return contactRepository.save(existingContact);

	}

	@Override
	@Transactional
	public void deleteContact(Long contact_id) {
		contact c = getContactById(contact_id);
		List<task> tasks = tservice.findByContactTask(c);
		tasks.forEach(tsk ->
			tservice.clearContact(tsk.getId())
		);
		contactRepository.deleteById(contact_id);
	}

	@Override
	public Optional<contact> findByName(String name) {
		return contactRepository.findByName(name);
	}
}
