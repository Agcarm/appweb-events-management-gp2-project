package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.services.contactService;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/contactRest")
@RestController
public class contactRestController {
    @Autowired
    contactService contactService ;

    @GetMapping("/all")
    public List<contact> showContacts() {
    	return contactService.getAllcontacts();
    }

    @PostMapping("/{id}/update")
    public contact updateContact(@PathVariable("id") Long contactId, @RequestBody contact updatedContact) {
        return contactService.updateContact(contactId, updatedContact);
    }

    @PostMapping("/save-project")
    public contact saveContact(@RequestBody contact contact) {
   	 return contactService.saveContact(contact);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteContact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
    }

    @GetMapping("/getByName/{name}")
    public contact getContact(@PathVariable("name") String name) {
        return contactService.findByName(name).get();
    }
    
    @GetMapping("/{id}")
    public contact getContact(@PathVariable("id") Long contactId){
        return contactService.getContactById(contactId);
    }
}
