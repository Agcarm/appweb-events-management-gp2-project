package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gp2.appwebeventsmanagementgp2.models.contact ;
import com.gp2.appwebeventsmanagementgp2.services.contactService;

@Controller
@RequestMapping("/contacts")
public class contactController {

    @Autowired
    private contactService contactService;

    @GetMapping({"/", ""})
    public String showContactS(Model model) {
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact";
    }

    @GetMapping("/{id}/edit")
    public String editContactForm(@PathVariable("id") Long contactId, Model model) {
        contact contact = contactService.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editCon";
    }

    @PostMapping("/{id}/update")
    public String updateContact(@PathVariable("id") Long contactId, @ModelAttribute("contact") contact updatedContact, Model model) {
        contactService.updateContact(contactId, updatedContact);
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact";
    }


    @PostMapping("/save-project")
    public String saveContact(@ModelAttribute contact contact, Model model) {
   	    contactService.saveContact(contact);
        model.addAttribute("contactList",contactService.getAllcontacts());
    	return "Contact";
    }

    @GetMapping("/{id}/delete")
    public String deleteContact(@PathVariable("id") Long contactId, Model model) {
        System.out.println(contactId);
        contactService.deleteContact(contactId);
        model.addAttribute("contactList",contactService.getAllcontacts());
        return "Contact"; // Redirect to the contact list page
    }
}
