package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.services.contactService;

@RequestMapping("/contactRest")
@RestController
public class contactRestController {
    @Autowired
    contactService contactService ;

    @GetMapping("/{id}/edit")
    public contact editContactForm(@PathVariable("id") Long contactId, Model model) {
        return contactService.getContactById(contactId);
    }

    @GetMapping("/all")
    public List<contact> showContacts(Model model) {
    	return contactService.getAllcontacts();
    }

    @PostMapping("/{id}/update")
    public contact updateContact(@PathVariable("id") Long contactId, @ModelAttribute("contact") contact updatedContact) {
        return contactService.updateContact(contactId, updatedContact);
    }

    @PostMapping("/save-project")
    public contact saveContact(@ModelAttribute contact contact) {
   	 return contactService.saveContact(contact);
    }

    @GetMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
        return "redirect:/Contact"; // Redirect to the contact list page
    }
}
